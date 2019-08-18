package cn.sevenyuan.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动类
 * @author JingQ
 */

@SpringBootApplication(scanBasePackages = {"cn.sevenyuan.demo.*"})
@ServletComponentScan
@MapperScan(basePackages = "cn.sevenyuan.demo.mapper")
@EnableCaching
public class SpringBootLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnApplication.class, args);
    }

}
