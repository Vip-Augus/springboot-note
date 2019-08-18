package cn.sevenyuan.demo.service.impl;

import cn.sevenyuan.demo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件发送实现类
 *
 * @author JingQ at 2019-08-18
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(String from, String to, String cc, String subject, String content) {
        SimpleMailMessage simMsg = new SimpleMailMessage();
        simMsg.setFrom(from);
        simMsg.setTo(to);
        simMsg.setCc(cc);
        simMsg.setSubject(subject);
        simMsg.setText(content);
        javaMailSender.send(simMsg);
    }

    @Override
    public void sendMailWithImg(String from, String to, String subject, String content, String[] srcPath, String[] resIds) {
        if (srcPath.length != resIds.length) {
            System.out.println("发送失败");
            return;
        }
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            for (int i = 0; i < srcPath.length; i++) {
                // 构造静态资源
                FileSystemResource res = new FileSystemResource((new File(srcPath[i])));
                // 将资源加入邮件对象中
                helper.addInline(resIds[i], res);
            }
            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println();
        }
    }

    @Override
    public void sendHtmlMail(String from, String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("发送邮件失败");
            log.error("发送邮件失败", e);
        }
    }

}
