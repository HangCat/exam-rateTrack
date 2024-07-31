package com.finpoints.framework.ratetrack.service;

/**
 * @author zhouyp
 * @program rateTrack
 * @description 发送邀请评分服务
 * @create 2024-07-31
 */
public interface RateInviteSendService {

    /**
     * 发送邀请评分（邮箱或者短信）
     * @param recipient 发送邀请的对象 （邮箱或者短信）
     * @param uniqueId 唯一id用于确定是哪个唯一链接，通过次id可查看链接的访问次数
     */
    void sendRatingMsg(String recipient,
                       String uniqueId);

}
