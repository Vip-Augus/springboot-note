package cn.sevenyuan.demo.service.impl;

import cn.sevenyuan.demo.domain.User;
import cn.sevenyuan.demo.mapper.UserMapper;
import cn.sevenyuan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JingQ at 2019-08-17
 */
@Service
@CacheConfig(cacheNames = "userCache")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable
    public User getById(Integer id) {
        // @Cacheable 表示对该方法进行缓存，默认情况下，缓存的 key 是方法的参数，缓存的 value 是方法的返回值
        // 如果是该类中的其它方法调用该方法，缓存不会生效，因为底层实现是通过 aop 增强方法，编译时自调用不会生效
        return userMapper.getById(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public int deleteById(Integer id) {
        // @CacheEvict 一般用于删除方法上，表示移除一个 key 对应的缓存
        userMapper.deleteById(id);
        return 0;
    }

    @Override
    public User insert(User user) {
        return null;
    }

    @Override
    @CachePut(key = "#user.id")
    public int updateById(User user) {
        // @CachePut 注解用户数据更新方法上，该注解在执行时，都不去检查缓存中是否有数据
        // 而是直接执行方法，然后将数据缓存起来
        if (user == null || user.getId() == null) {
            throw new RuntimeException("没有用户信息");
        }
        return userMapper.updateById(user);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
