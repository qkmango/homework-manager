package cn.qkmango.hm.system.service.impl;

import cn.qkmango.hm.system.dao.UserDao;
import cn.qkmango.hm.system.domain.User;
import cn.qkmango.hm.system.service.UserService;
import cn.qkmango.hm.utils.SqlSessionUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className UserServiceImpl
 * @author: Mango
 * @date: 2021-02-21 14:09
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public Map<String, Object> login(String username, String password) {

        User user = userDao.login(username,password);
        Map<String, Object> map = new HashMap<>();

        if (user != null) {
            map.put("success",true);
            map.put("user",user);
        } else {
            map.put("success",false);
        }

        return map;
    }
}
