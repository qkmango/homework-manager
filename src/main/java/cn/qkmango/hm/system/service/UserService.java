package cn.qkmango.hm.system.service;

import java.util.Map;

/**
 * @version 1.0
 * <p>UserService</p>
 * <p>User服务接口 User相关的服务都有此类进行</p>
 * @className UserService
 * @author: Mango
 * @date: 2021-02-21 14:09
 */
public interface UserService {

    Map<String, Object> login(String username, String password);
}
