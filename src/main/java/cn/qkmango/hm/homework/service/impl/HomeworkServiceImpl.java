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
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className HomeworkServiceImpl
 * @author: Mango
 * @date: 2021-02-22 10:06
 */
public class HomeworkServiceImpl implements HomeworkService {
    CourseDao   courseDao   = SqlSessionUtil.getSqlSession().getMapper(CourseDao.class);
    HomeworkDao homeworkDao = SqlSessionUtil.getSqlSession().getMapper(HomeworkDao.class);

    @Override
    public List<Course> getCourseList() {
        List<Course> courseList = courseDao.getCourseList();
        return courseList;
    }

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

    @Override
    public boolean getHomeworkIsCommit(String uid, String hid) {

        int count = homeworkDao.getHomeworkIsCommit(uid, hid);

        if (count==0) {
            return false;
        } else {
            return true;
        }

    }

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
