package cn.qkmango.hm.visualization.service;

import cn.qkmango.hm.utils.RespMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * <p>VisualizationService</p>
 * <p>数据可视化服务 接口</p>
 * @className VisualizationService
 * @author: Mango
 * @date: 2021-03-06 20:43
 */
public interface VisualizationService {
    RespMap<Object> getRecentCommitCount();

    List<Map<String, Object>> getCommitDynamic();

    List<HashMap<String, String>> getHeatmap(HashMap<String, String> map);

    List<HashMap<String, Integer>> getCourseHomeworkProportion();
}
