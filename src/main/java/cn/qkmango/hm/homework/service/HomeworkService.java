package cn.qkmango.hm.homework.service;

import cn.qkmango.hm.exception.HomeworkException;
import cn.qkmango.hm.homework.domain.Course;
import cn.qkmango.hm.homework.domain.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className HomeworkService
 * @author: Mango
 * @date: 2021-02-22 10:06
 */
public interface HomeworkService {
    List<Course> getCourseList();

    Map<String, Object> getHomeworkById(String id);

    boolean addHomeWork(@Param("homework") Homework homework) throws HomeworkException;

    Map<String, Object> getHomeworkPageList(Map<String, Object> map);

    boolean getHomeworkIsCommit(String uid, String hid);
}
