package cn.qkmango.hm.homework.service;

import cn.qkmango.hm.homework.domain.Format;

import java.util.List;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className FormatService
 * @author: Mango
 * @date: 2021-03-10 11:33
 */
public interface FormatService {
    /**
     * 获取 format 格式化表信息
     * @return 返回 List<Format> 格式化列表
     */
    List<Format> getFormat();
}
