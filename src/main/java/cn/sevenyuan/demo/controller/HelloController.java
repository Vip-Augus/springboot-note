package cn.sevenyuan.demo.controller;

import cn.sevenyuan.demo.config.HelloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JingQ at 2019-08-06
 */
@RestController
public class HelloController {

    @Autowired
    private HelloConfig helloConfig;

    @GetMapping("hello")
    public String sayHello() {
        return helloConfig.sayHello("debug the world");
    }
}
