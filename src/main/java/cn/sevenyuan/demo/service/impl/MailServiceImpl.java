package cn.sevenyuan.demo.service.impl;

import cn.sevenyuan.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 邮件发送实现类
 *
 * @author JingQ at 2019-08-18
 */
@Service
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

    }

}
