package cn.qkmango.hm.homework.service.impl;

import cn.qkmango.hm.exception.HomeworkException;
import cn.qkmango.hm.homework.dao.CourseDao;
import cn.qkmango.hm.homework.dao.HomeworkDao;
import cn.qkmango.hm.homework.domain.Course;
import cn.qkmango.hm.homework.domain.Homework;
import cn.qkmango.hm.homework.service.HomeworkService;
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
    public Map<String, Object> getHomeworkById(String id) {

        Homework homework = homeworkDao.getHomeworkById(id);
        HashMap<String, Object> map = new HashMap<>();

        if (homework != null) {
            map.put("success",true);
            map.put("homework",homework);
        } else {
            map.put("success",false);
        }

        return map;
    }

    @Override
    public boolean addHomeWork(Homework homework) throws HomeworkException {
        int count = homeworkDao.addHomeWork(homework);
        if (count != 1) {
            throw new HomeworkException("添加Homework失败！");
        }
        return true;
    }

}