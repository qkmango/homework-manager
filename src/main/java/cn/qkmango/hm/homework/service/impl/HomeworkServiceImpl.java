package cn.qkmango.hm.homework.service.impl;
import cn.qkmango.hm.exception.HomeworkException;
import cn.qkmango.hm.homework.dao.CourseDao;
import cn.qkmango.hm.homework.dao.HomeworkDao;
import cn.qkmango.hm.homework.domain.Course;
import cn.qkmango.hm.homework.domain.Homework;
import cn.qkmango.hm.homework.service.HomeworkService;
import cn.qkmango.hm.utils.RespMap;
import cn.qkmango.hm.utils.RespStatusMsg;
import cn.qkmango.hm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * <p>HomeworkServiceImpl</p>
 * <p>作业服务，如作业的发布，获取作业信息 实现类</p>
 * @className HomeworkServiceImpl
 * @author: Mango
 * @date: 2021-02-22 10:06
 */
public class HomeworkServiceImpl implements HomeworkService {
    CourseDao   courseDao   = SqlSessionUtil.getSqlSession().getMapper(CourseDao.class);
    HomeworkDao homeworkDao = SqlSessionUtil.getSqlSession().getMapper(HomeworkDao.class);

    /**
     * 获取学科列表
     * @return List<Course>集合，包含course表的所有记录
     */
    @Override
    public List<Course> getCourseList() {
        List<Course> courseList = courseDao.getCourseList();
        return courseList;
    }

    /**
     * 获取一个作业对象（作业的信息），通过作业ID
     * @param id 作业的id（homework.id）
     * @return Map集合，包含是否获取成功的标志位、返回给前端的信息、homework对象
     */
    @Override
    public RespMap<Object> getHomeworkById(String id) {

        Homework homework = homeworkDao.getHomeworkById(id);
        RespMap<Object> map = new RespMap<>();

        if (homework != null) {
            map.putSuccess(true);
            map.putMsg(RespStatusMsg.Get_Homework_Success);
            map.put("homework",homework);
        } else {
            map.put("success",false);
            map.putMsg(RespStatusMsg.Get_Homework_Fail);
        }

        return map;
    }

    @Override
    public RespMap<Object> addHomeWork(Homework homework) throws Throwable {
        int count = homeworkDao.addHomeWork(homework);
        RespMap<Object> map = new RespMap<Object>();

        try {
            if (count==1) {
                map.putSuccess(true);
                map.putMsg(RespStatusMsg.Homework_Add_Success);
            }else {
                map.putSuccess(false);
                map.putMsg(RespStatusMsg.Homework_Add_Fail);
                throw new HomeworkException(RespStatusMsg.Homework_Add_Fail);
            }
        } catch (HomeworkException e) {
            e.printStackTrace();
            throw e.getCause();
        } finally {
            return map;
        }
    }

    /**
     * 分页 获取作业列表
     * @param map Map集合，包含layui分页组件的所需数据规范，包含 count（总记录条数）、code（状态码，0为正常）、data（分页数据 homework list对象）
     * @return
     */
    @Override
    public RespMap<Object> getHomeworkPageList(Map<String, Object> map) {
        List<Homework> list = homeworkDao.getHomeworkPageList(map);
        int count = homeworkDao.getTotalByCondition();

        RespMap<Object> resultMap = new RespMap<Object>();
        resultMap.put("code",0);
        resultMap.put("count",count);
        resultMap.putData(list);

        return resultMap;
    }

    /**
     * 获取此作业是否已经提交了
     * @param uid 用户的id
     * @param hid 作业的id
     * @return 返回boolean，是否已经提交
     */
    @Override
    public boolean getHomeworkIsCommit(String uid, String hid) {

        int count = homeworkDao.getHomeworkIsCommit(uid, hid);

        if (count==0) {
            return false;
        } else {
            return true;
        }

    }

    /**
     *
     * @param hid 作业的id
     * @return Map对象，包含是否编辑成功的标志位和返回给前端的提示信息
     * @throws Exception
     */
    @Override
    public RespMap<Object> deleteHomework(String hid) {

        RespMap<Object> map = new RespMap<>();

        try {
            int count = homeworkDao.deleteHomework(hid);

            if (count==1) {
                map.putSuccess(true);
                map.putMsg(RespStatusMsg.Del_Homework_Success);
            } else {
                map.putSuccess(false);
                map.putMsg(RespStatusMsg.Del_Homework_Fail);
                throw new HomeworkException(RespStatusMsg.Del_Homework_Fail);
            }
        } catch (HomeworkException e){
            e.printStackTrace();
            throw e.getCause();
        } finally {
            return map;
        }
    }

    /**
     * 获取作业信息（管理员编辑作业信息时使用）
     * @param hid 作业的id
     * @return 返回是否获取成功的标志位 和 作业对象（如果获取成功的话）
     */
    @Override
    public RespMap<Object> getHomeworkByIdOfEdit(String hid) {

        Homework homework = homeworkDao.getHomeworkByIdOfEdit(hid);
        RespMap<Object> map = new RespMap<>();

        if (homework != null) {
            map.putSuccess(true);
            map.put("homework",homework);
        } else {
            map.putSuccess(false);
        }

        return map;
    }

    /**
     * 编辑已经发布的作业（管理员）
     * @param homework 编辑后作业对象
     * @return 是否编辑成功的标志位和信息
     * @throws Throwable
     */
    @Override
    public RespMap<Object> editHomework(Homework homework) throws Throwable {

        RespMap<Object> map = new RespMap<>();

        try {
            int count = homeworkDao.editHomework(homework);
            if (count == 1) {
                map.putSuccess(true);
                map.putMsg(RespStatusMsg.Edit_Homework_Success);
            } else {
                map.putSuccess(false);
                map.putMsg(RespStatusMsg.Edit_Homework_Fail);
                throw new HomeworkException(RespStatusMsg.Edit_Homework_Fail);
            }

        } catch (HomeworkException e) {
            e.printStackTrace();
            throw e.getCause();
        } finally {
            return map;
        }
    }

    /**
     * 分页获取 作业列表（管理员页面），同时带有一提交人数的信息
     * @param map 要查询的分页信息
     * @return 返回 分页信息和作业列表（带有已提交人数的信息）
     */
    @Override
    public RespMap<Object> getHomeworkAndCommitCountPageList(Map<String, Object> map) {
        List<HashMap<String, Object>> list = homeworkDao.getHomeworkAndCommitCountPageList(map);
        int count = homeworkDao.getTotalByCondition();

        RespMap<Object> resultMap = new RespMap<>();


        resultMap.put("code",0);
        resultMap.putCount(count);
        resultMap.putData(list);

        return resultMap;
    }

}
