package cn.qkmango.hm.system.service;

import java.util.Map;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className UserService
 * @author: Mango
 * @date: 2021-02-21 14:09
 */
public interface UserService {

    Map<String, Object> login(String username, String password);
}
