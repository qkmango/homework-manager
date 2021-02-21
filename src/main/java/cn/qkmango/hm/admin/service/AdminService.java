package cn.qkmango.hm.admin.service;

import cn.qkmango.hm.Exception.HomeworkException;
import cn.qkmango.hm.pub.domain.Homework;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className AdminService
 * @author: Mango
 * @date: 2021-02-21 18:59
 */
public interface AdminService {
    boolean addHomeWork(@Param("homework") Homework homework) throws HomeworkException;
}
