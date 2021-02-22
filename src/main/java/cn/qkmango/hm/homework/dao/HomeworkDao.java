package cn.qkmango.hm.homework.dao;

import cn.qkmango.hm.homework.domain.Homework;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className HomeworkDao
 * @author: Mango
 * @date: 2021-02-21 19:00
 */
public interface HomeworkDao {
    int addHomeWork(Homework homework);

    Homework getHomeworkById(String id);
}
