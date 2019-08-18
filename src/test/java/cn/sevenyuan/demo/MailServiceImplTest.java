package cn.sevenyuan.demo;

import cn.sevenyuan.demo.domain.Book;
import cn.sevenyuan.demo.domain.User;
import cn.sevenyuan.demo.service.MailService;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author JingQ at 2019-08-18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendMailWithImg() {
        mailService.sendMailWithImg("augus.ye@qq.com","2225204153@qq.com","测试邮件（带图片",
                "<div>hello, 这是一封带图片资源的邮件:" +
                "这是图片 1：<div><img src='cid:p01'/></div>" +
                "这是图片 2：<div><img src='cid:p02'/></div>" +
                "</div>",
                new String[]{"/Users/yejingqi/Deploy/Blog/图片分类/mail/mail_sender.png",
                "/Users/yejingqi/Deploy/Blog/图片分类/mail/demo.png"},
                new String[]{"p01", "p02"});
    }

    @Test
    public void sendHtmlMail() {
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            ClassLoader loader = SpringBootLearnApplication.class.getClassLoader();
            configuration.setClassLoaderForTemplateLoading(loader, "ftl");
            Template template = configuration.getTemplate("mailtemplate.ftl");
            StringWriter mail = new StringWriter();
            User user = new User();
            user.setName("一叶知秋");
            user.setAge(23);
            template.process(user, mail);
            mailService.sendHtmlMail("augus.ye@qq.com", "2225204153@qq.com", "Html样式的邮件", mail.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendThymeleafMail() {
        Context context = new Context();
        context.setVariable("subject", "图书清册");
        List<Book> books = Lists.newArrayList();
        books.add(new Book("Go 语言基础", 1, "nonename", BigDecimal.TEN));
        books.add(new Book("Go 语言实战", 2, "nonename", BigDecimal.TEN));
        books.add(new Book("Go 语言进阶", 3, "nonename", BigDecimal.TEN));
        context.setVariable("books", books);
        String mail = templateEngine.process("mailtemplate.html", context);
        mailService.sendHtmlMail("augus.ye@qq.com", "2225204153@qq.com", "图书清册", mail);
    }

}
