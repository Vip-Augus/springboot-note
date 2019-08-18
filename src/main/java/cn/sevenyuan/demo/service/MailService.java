package cn.sevenyuan.demo.service;

/**
 * 邮件服务
 *
 * @author JingQ at 2019-08-18
 */
public interface MailService {

    /**
     * 发送简单邮件
     * @param from      发送人地址
     * @param to        收件人地址
     * @param cc        抄送人地址
     * @param subject   主题
     * @param content   内容
     */
    void sendSimpleMail(String from, String to, String cc, String subject, String content);


    /**
     * 发送简单邮件
     * @param from      发送人地址
     * @param to        收件人地址
     * @param subject   主题
     * @param content   内容
     * @param srcPath   资源地址
     * @param resIds    资源 ID
     */
    void sendMailWithImg(String from, String to, String subject, String content, String[] srcPath, String[] resIds);

    /**
     * 发送 html 邮件
     * @param from      from
     * @param to        to
     * @param subject   主题
     * @param content   html 内容
     */
    void sendHtmlMail(String from, String to, String subject, String content);
}
