package cn.qkmango.hm.homework.dao;

import cn.qkmango.hm.homework.domain.Course;

import java.util.List;

/**
 * @version 1.0
 * <p>CourseDao</p>
 * <p>course 表的数据访问层</p>
 * @className CourseDao
 * @author: Mango
 * @date: 2021-02-22 09:58
 */
public interface CourseDao {
    List<Course> getCourseList();
}
