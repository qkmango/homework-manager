package cn.qkmango.hm.homework.service.impl;

import cn.qkmango.hm.homework.dao.CourseDao;
import cn.qkmango.hm.homework.domain.Course;
import cn.qkmango.hm.homework.service.HomeworkService;
import cn.qkmango.hm.utils.SqlSessionUtil;
import java.util.List;

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
    CourseDao courseDao = SqlSessionUtil.getSqlSession().getMapper(CourseDao.class);

    @Override
    public List<Course> getCourseList() {
        List<Course> courseList = courseDao.getCourseList();
        return courseList;
    }
}
