package cn.sevenyuan.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * simple 配置
 * @author JingQ at 2019-08-06
 */

public class HelloConfig {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}
