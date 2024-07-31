package com.finpoints.framework.ratetrack.service.impl;

import com.finpoints.framework.ratetrack.service.RateInviteSendService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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
@Qualifier("emailService")
public class EmailService implements RateInviteSendService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine springTemplateEngine;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine springTemplateEngine) {
        this.mailSender = mailSender;
        this.springTemplateEngine = springTemplateEngine;
    }


    public void sendRatingMsg(String recipientEmail, String uniqueId) {
        // 创建邮件内容
        Context context = new Context();
        context.setVariable("uniqueId", uniqueId);
        context.setVariable("accessSource", "email");

        String emailContent = springTemplateEngine.process(
                "main_page_template", context);

        // 创建邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(
                    message, true, "UTF-8");
            helper.setTo(recipientEmail);
            helper.setSubject("请为我们评分");
            helper.setText(emailContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // 发送邮件
        mailSender.send(message);
    }
}
