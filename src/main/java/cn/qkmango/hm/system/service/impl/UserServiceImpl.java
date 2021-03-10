package cn.qkmango.hm.system.service.impl;

import cn.qkmango.hm.exception.UserException;
import cn.qkmango.hm.system.dao.UserDao;
import cn.qkmango.hm.system.domain.User;
import cn.qkmango.hm.system.service.UserService;
import cn.qkmango.hm.utils.SqlSessionUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * <p>UserServiceImpl</p>
 * <p>User服务实现类 User相关的服务都有此类进行</p>
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

    @Override
    public boolean change(HashMap<String, String> map) throws Throwable {

        boolean flag = false;

        try {
            int count = userDao.change(map);
            if (count != 1) {
                throw new UserException("修改用户信息失败！");
            }
            flag = true;
        } catch (UserException e) {
            e.printStackTrace();
            throw e.getCause();
        }catch (Exception e) {
            e.printStackTrace();
            throw e.getCause();
        } finally {
            return flag;
        }
    }
}
