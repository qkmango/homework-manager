package cn.qkmango.hm.system.dao;

import cn.qkmango.hm.system.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className UserDao
 * @author: Mango
 * @date: 2021-02-21 14:11
 */
public interface UserDao {


    User login(@Param("username") String username,
               @Param("password")String password);
}
