package com.chen.app.email;

import com.chen.app.data.InlineResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Slf4j
@Component
public class SendEmailHelper {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String formMail;

    public void sendHtmlMail(String toMail,String subject,String content) {
        MimeMessage mimeMessage = sender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setFrom(formMail);
            mimeMessageHelper.setText(content,true);
            mimeMessageHelper.setSubject(subject);
            sender.send(mimeMessage);
            log.info("发送给"+toMail+"html邮件已经发送。 subject："+subject);
        } catch (MessagingException e) {
            log.info("发送给"+toMail+"html send mail error subject："+subject);
            e.printStackTrace();
        }
    }


    public void sendInlineResourceMail(String to, String subject, String content, List<InlineResource> resourceist) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(formMail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            for (InlineResource inlineResource : resourceist) {
                FileSystemResource res = new FileSystemResource(new File(inlineResource.getPath()));
                helper.addInline(inlineResource.getCid(),res);
            }
            sender.send(message);
            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }
}
