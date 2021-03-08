package cn.qkmango.hm.homework.service;

import cn.qkmango.hm.exception.CommitHomeworkException;
import cn.qkmango.hm.exception.HomeworkException;
import cn.qkmango.hm.homework.domain.CommitHomework;
import cn.qkmango.hm.homework.domain.Course;
import cn.qkmango.hm.homework.domain.Homework;
import cn.qkmango.hm.utils.RespMap;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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

    RespMap<Object> getHomeworkById(String id);

    RespMap<Object> addHomeWork(@Param("homework") Homework homework) throws Throwable;

    RespMap<Object> getHomeworkPageList(Map<String, Object> map);

    boolean getHomeworkIsCommit(String uid, String hid);

    RespMap<Object> deleteHomework(String hid) throws Exception;

    RespMap<Object> getHomeworkByIdOfEdit(String hid);

    RespMap<Object> editHomework(@Param("homework") Homework homework) throws Throwable;

    RespMap<Object> getHomeworkAndCommitCountPageList(Map<String, Object> map);
}
