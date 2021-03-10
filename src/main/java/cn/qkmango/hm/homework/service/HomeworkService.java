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
 * <p>HomeworkService</p>
 * <p>作业服务，如作业的发布，获取作业信息</p>
 * @className HomeworkService
 * @author: Mango
 * @date: 2021-02-22 10:06
 */
public interface HomeworkService {
    /**
     * 获取学科列表
     * @return List<Course>集合，包含course表的所有记录
     */
    List<Course> getCourseList();

    /**
     * 获取一个作业对象（作业的信息），通过作业ID
     * @param id 作业的id（homework.id）
     * @return Map集合，包含是否获取成功的标志位、返回给前端的信息、homework对象
     */
    RespMap<Object> getHomeworkById(String id);

    /**
     * 添加作业（管理员）
     * @param homework 作业对象
     * @return Map集合，包含是否获取成功的标志位、返回给前端的信息
     * @throws Throwable
     */
    RespMap<Object> addHomeWork(@Param("homework") Homework homework) throws Throwable;

    /**
     * 分页 获取作业列表
     * @param map Map集合，包含layui分页组件的所需数据规范，包含 count（总记录条数）、code（状态码，0为正常）、data（分页数据 homework list对象）
     * @return
     */
    RespMap<Object> getHomeworkPageList(Map<String, Object> map);

    /**
     * 获取此作业是否已经提交了
     * @param uid 用户的id
     * @param hid 作业的id
     * @return 返回boolean，是否已经提交
     */
    boolean getHomeworkIsCommit(String uid, String hid);

    /**
     * 删除已经发布的作业（管理员）
     * @param hid 作业的id
     * @return Map对象，包含是否编辑成功的标志位和返回给前端的提示信息
     * @throws Exception
     */
    RespMap<Object> deleteHomework(String hid) throws Exception;

    /**
     * 获取作业信息（管理员编辑作业信息时使用）
     * @param hid 作业的id
     * @return 返回是否获取成功的标志位 和 作业对象（如果获取成功的话）
     */
    RespMap<Object> getHomeworkByIdOfEdit(String hid);

    /**
     * 编辑已经发布的作业（管理员）
     * @param homework 编辑后作业对象
     * @return 是否编辑成功的标志位和信息
     * @throws Throwable
     */
    RespMap<Object> editHomework(@Param("homework") Homework homework) throws Throwable;

    /**
     * 分页获取 作业列表（管理员页面），同时带有一提交人数的信息
     * @param map 要查询的分页信息
     * @return 返回 分页信息和作业列表（带有已提交人数的信息）
     */
    RespMap<Object> getHomeworkAndCommitCountPageList(Map<String, Object> map);
}
