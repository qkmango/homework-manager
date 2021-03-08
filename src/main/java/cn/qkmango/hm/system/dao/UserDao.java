package cn.qkmango.hm.system.dao;

import cn.qkmango.hm.system.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * <p>UserDao</p>
 * <p>user表的数据访问层</p>
 * @className UserDao
 * @author: Mango
 * @date: 2021-02-21 14:11
 */
public interface UserDao {

    int getUserCount();

    User login(@Param("username") String username,
               @Param("password")String password);
}
