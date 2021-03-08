package cn.qkmango.hm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * <p>RespMap</p>
 * <p>此类继承 HashMap，添加了一些方法，以便更加方便的在Map中添加 key-value </p>
 * @className RespMap
 * @author: Mango
 * @date: 2021-03-04 10:52
 */
public class RespMap<V> extends HashMap<String,V> {

    /**
     * 添加标志位
     * @param flag 标志位值
     * @return
     */
    public V putSuccess(V flag) {
        return super.put("success", flag);
    }

    /**
     * 添加消息
     * @param msg 消息
     * @return
     */
    public V putMsg(V msg) {
        return super.put("msg", msg);
    }

    /**
     * 添加数据
     * @param data 数据对象
     * @return
     */
    public V putData(V data) {
        return super.put("data", data);
    }

    /**
     * 添加count计数
     * @param count 计数值
     * @return
     */
    public V putCount(V count) {
        return super.put("count", count);
    }

}
