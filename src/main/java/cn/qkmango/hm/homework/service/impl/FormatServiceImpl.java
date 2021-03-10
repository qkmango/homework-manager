package cn.qkmango.hm.homework.service.impl;

import cn.qkmango.hm.homework.dao.CourseDao;
import cn.qkmango.hm.homework.dao.FormatDao;
import cn.qkmango.hm.homework.domain.Format;
import cn.qkmango.hm.homework.service.FormatService;
import cn.qkmango.hm.utils.SqlSessionUtil;

import java.util.List;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className FormatServiceImpl
 * @author: Mango
 * @date: 2021-03-10 11:34
 */
public class FormatServiceImpl implements FormatService {


    FormatDao formatDao   = SqlSessionUtil.getSqlSession().getMapper(FormatDao.class);

    @Override
    public List<Format> getFormat() {

        List<Format> list = formatDao.getFormat();

        return list;
    }
}
