package cn.qkmango.hm.homework.service.impl;

import cn.qkmango.hm.homework.dao.CourseDao;
import cn.qkmango.hm.homework.dao.UserDao;
import cn.qkmango.hm.homework.dao.VisualizationDao;
import cn.qkmango.hm.homework.service.VisualizationService;
import cn.qkmango.hm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className VisualizationServiceImpl
 * @author: Mango
 * @date: 2021-03-06 20:43
 */
public class VisualizationServiceImpl implements VisualizationService {

    VisualizationDao    visualizationDao = SqlSessionUtil.getSqlSession().getMapper(VisualizationDao.class);
    UserDao             userDao          = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    /**
     * 查询各科最近一次作业提交人数
     */
    @Override
    public HashMap<String, Object> getRecentCommitCount() {

        int count = userDao.getUserCount();
        List<HashMap<String, Object>> list= visualizationDao.getRecentCommitCount();

        HashMap<String, Object> map = new HashMap<>();

        map.put("count",count);
        map.put("data",list);

        return map;
    }
}
