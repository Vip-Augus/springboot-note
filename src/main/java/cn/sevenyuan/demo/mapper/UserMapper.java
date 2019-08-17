package cn.sevenyuan.demo.mapper;

import cn.sevenyuan.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JingQ at 2019-08-17
 */
@Mapper
public interface UserMapper {

    /**
     * 获取
     * @param id    id
     * @return      用户
     */
    User getById(Integer id);

    /**
     * 根据 id 删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 新增用户
     * @param user 用户信息
     * @return 带有主键的用户信息
     */
    User insert(User user);

    /**
     * 通过主键 id 进行更新信息
     * @param user  信息
     * @return      更新数量
     */
    int updateById(User user);

    /**
     * 获取全部用户信息
     * @return all
     */
    List<User> getAllUsers();
}
