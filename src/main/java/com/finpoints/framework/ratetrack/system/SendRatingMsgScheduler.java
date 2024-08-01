package com.finpoints.framework.ratetrack.system;

import com.finpoints.framework.ratetrack.model.UserInfo;
import com.finpoints.framework.ratetrack.model.UserRatingPageAccess;
import com.finpoints.framework.ratetrack.repository.UserAcccessRepository;
import com.finpoints.framework.ratetrack.repository.UserInfoRepository;
import com.finpoints.framework.ratetrack.service.RateInviteSendService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author zhouyp
 * @program rateTrack
 * @description
 * @create 2024-07-30
 */
@Component
public class SendRatingMsgScheduler {

    private final RateInviteSendService smsService;

    private final RateInviteSendService emailService;

    private final UserInfoRepository userInfoRepository;

    private final UserAcccessRepository userAcccessRepository;

    public SendRatingMsgScheduler(
            @Qualifier("smsService") RateInviteSendService smsService,
            @Qualifier("emailService") RateInviteSendService emailService,
            UserInfoRepository userInfoRepository,
            UserAcccessRepository userAcccessRepository) {
        this.smsService = smsService;
        this.emailService = emailService;
        this.userInfoRepository = userInfoRepository;
        this.userAcccessRepository = userAcccessRepository;
    }

    /**
     * 已经发送过消息，并且未评分的用户，修改状态为未发送
     * 每5个小时执行一次
     */
//    @Scheduled(cron = "0 0 5 * * * ?")
    @Transactional
    public void refreshUserSentStatus() {

        // 分页查询，避免一次查询数据太多，压垮DB
        int allHasSentTrue = userInfoRepository.countAllByHasSentTrue();
        int pages = allHasSentTrue / 20 + 1;

        for (int i = 0; i < pages; i++) {
            Page<UserInfo> hasSentUsers = userInfoRepository
                    .findAllByHasSentTrue(Pageable.ofSize(20));
            for (UserInfo userInfo : hasSentUsers) {
                List<UserRatingPageAccess> actions = userAcccessRepository
                        .findAllByUserId(userInfo.getId());
                boolean hasAccessed = actions.stream()
                        .anyMatch(action -> action.getAccessNum() > 0);
                // 如果一个邀请链接都没访问过，修改用户发送状态，待下次定时任务再重新发起
                if (!hasAccessed) {
                    userInfo.setHasSent(false);
                    userInfoRepository.save(userInfo);
                }
            }
        }
    }

    /**
     * 发送邀请信息
     */
//    @Scheduled(cron = "0 0 1 * * * ?")
    @Transactional
    public void send() {
        // 分页查询，避免一次查询数据太多，压垮DB
        int allHasSentFalse = userInfoRepository.countAllByHasSentFalse();
        int pages = allHasSentFalse / 20 + 1;

        for (int i = 0; i < pages; i++) {
            Page<UserInfo> notSendUsers = userInfoRepository
                    .findAllByHasSentFalse(Pageable.ofSize(20));
            boolean sendMail = true;
            for (UserInfo userInfo : notSendUsers) {
                // 生成唯一标识符
                String uniqueId = UUID.randomUUID().toString();
                // 1. 发送邀请评分消息
                // todo 根据策略选择使用什么方式通知 目前就一次邮件一次短信
                if (sendMail) {
                    emailService.sendRatingMsg(userInfo.getEmail(), uniqueId);
                    sendMail = false;
                } else {
                    smsService.sendRatingMsg(userInfo.getEmail(), uniqueId);
                    sendMail = true;
                }
                // 2. 修改用户状态为已发送
                userInfo.setHasSent(true);
                userInfoRepository.save(userInfo);
                // 3. UserAction表新增一条记录
                UserRatingPageAccess userRatingPageAccess = new UserRatingPageAccess();
                userRatingPageAccess.setUserId(userInfo.getId());
                userRatingPageAccess.setLinkCode(uniqueId);
                userRatingPageAccess.setAccessNum(0);
                userAcccessRepository.save(userRatingPageAccess);
            }
        }

    }

}
