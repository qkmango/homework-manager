package cn.qkmango.hm.admin.service.impl;

import cn.qkmango.hm.Exception.HomeworkException;
import cn.qkmango.hm.homework.dao.CourseDao;
import cn.qkmango.hm.admin.dao.HomeworkDao;
import cn.qkmango.hm.admin.service.AdminService;
import cn.qkmango.hm.homework.domain.Homework;
import cn.qkmango.hm.utils.SqlSessionUtil;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className AdminServiceImpl
 * @author: Mango
 * @date: 2021-02-21 18:59
 */
public class AdminServiceImpl implements AdminService {

    private HomeworkDao homeworkDao = SqlSessionUtil.getSqlSession().getMapper(HomeworkDao.class);
    private CourseDao   courseDao   = SqlSessionUtil.getSqlSession().getMapper(CourseDao.class);

    @Override
    public boolean addHomeWork(Homework homework) throws HomeworkException {
        int count = homeworkDao.addHomeWork(homework);
        if (count != 1) {
            throw new HomeworkException("添加Homework失败！");
        }
        return true;
    }



}
