package cn.qkmango.hm.visualization.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * <p>VisualizationDao</p>
 * <p>数据可视化模块的数据访问层，此数据访问层操作多长表</p>
 * @className VisualizationDao
 * @author: Mango
 * @date: 2021-03-06 20:47
 */
public interface VisualizationDao {
    List<HashMap<String, Object>> getRecentCommitCount();

    List<Map<String, Object>> getCommitDynamic();

    List<HashMap<String, String>> getHeatmap(HashMap<String, String> map);

    List<HashMap<String, Integer>> getCourseHomeworkProportion();
}
