package cn.sevenyuan.demo.service;

import cn.sevenyuan.demo.domain.User;

import java.util.List;

/**
 * @author JingQ at 2019-08-17
 */
public interface UserService {

    User getById(Integer id);

    int deleteById(Integer id);

    User insert(User user);

    int updateById(User user);

    List<User> getAllUsers();
}
