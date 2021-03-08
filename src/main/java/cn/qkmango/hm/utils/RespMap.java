package cn.qkmango.hm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className RespMap
 * @author: Mango
 * @date: 2021-03-04 10:52
 */
public class RespMap<V> extends HashMap<String,V> {

    public V putSuccess(V flag) {
        return super.put("success", flag);
    }

    public V putMsg(V msg) {
        return super.put("msg", msg);
    }

    public V putData(V data) {
        return super.put("data", data);
    }

    public V putCount(V count) {
        return super.put("count", count);
    }

}
