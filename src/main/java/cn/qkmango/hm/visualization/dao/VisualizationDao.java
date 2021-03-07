package cn.qkmango.hm.visualization.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className VisualizationDao
 * @author: Mango
 * @date: 2021-03-06 20:47
 */
public interface VisualizationDao {
    List<HashMap<String, Object>> getRecentCommitCount();

    List<Map<String, Object>> getCommitDynamic();
}
