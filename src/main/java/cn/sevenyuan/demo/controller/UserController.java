package cn.sevenyuan.demo.controller;

import cn.sevenyuan.demo.domain.User;
import cn.sevenyuan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JingQ at 2019-08-17
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public User getById(@RequestParam("id") Integer id) {
        return userService.getById(id);
    }
}
