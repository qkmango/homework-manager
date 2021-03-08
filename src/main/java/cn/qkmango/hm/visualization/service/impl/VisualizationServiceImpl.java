package cn.qkmango.hm.visualization.service.impl;

import cn.qkmango.hm.homework.dao.CommitHomeworkDao;
import cn.qkmango.hm.homework.dao.UserDao;

import cn.qkmango.hm.utils.RespMap;
import cn.qkmango.hm.utils.SqlSessionUtil;
import cn.qkmango.hm.visualization.dao.VisualizationDao;
import cn.qkmango.hm.visualization.service.VisualizationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    VisualizationDao    visualizationDao   = SqlSessionUtil.getSqlSession().getMapper(VisualizationDao.class);
    UserDao             userDao            = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    /**
     * 查询各科最近一次作业提交人数
     */
    @Override
    public RespMap<Object> getRecentCommitCount() {

        int count = userDao.getUserCount();
        List<HashMap<String, Object>> list= visualizationDao.getRecentCommitCount();

        RespMap<Object> map = new RespMap<>();

        map.putCount(count);
        map.putData(list);

        return map;
    }


    /**
     * 获取作业提交动态
     * @return
     */
    @Override
    public List<Map<String, Object>> getCommitDynamic() {
        List<Map<String, Object>> list = visualizationDao.getCommitDynamic();
        return list;
    }

    @Override
    public List<HashMap<String, String>> getHeatmap(HashMap<String, String> map) {
        List<HashMap<String, String>> list = visualizationDao.getHeatmap(map);

        return list;
    }
}
