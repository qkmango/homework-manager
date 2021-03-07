package cn.qkmango.hm.visualization.service;

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
public interface VisualizationService {
    HashMap<String, Object> getRecentCommitCount();

    List<Map<String, Object>> getCommitDynamic();
}
