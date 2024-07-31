package com.finpoints.framework.ratetrack.service.impl;

import com.finpoints.framework.ratetrack.service.RateInviteSendService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 * @author zhouyp
 * @program rateTrack
 * @description
 * @create 2024-07-30
 */
@Service
@Qualifier("smsService")
@Slf4j
public class SMSService implements RateInviteSendService {


    /**
     * 因为一般sms服务都需要接入第三方才能实现，这里就简单打印一行日志输出
     */
    @Override
    public void sendRatingMsg(String recipientPhone, String uniqueId) {
        log.info("Sending rating recipientPhone to:[{}], uniqueId:[{}] ",
                recipientPhone, uniqueId);
    }
}
