package cn.qkmango.hm.homework.service.impl;

import cn.qkmango.hm.homework.dao.CommitHomeworkDao;
import cn.qkmango.hm.homework.domain.CommitHomework;
import cn.qkmango.hm.homework.service.CommitHomeworkService;
import cn.qkmango.hm.system.domain.AliOss;
import cn.qkmango.hm.system.service.OSSService;
import cn.qkmango.hm.system.service.impl.OSSServiceImpl;
import cn.qkmango.hm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className CommitHomeworkServiceImpl
 * @author: Mango
 * @date: 2021-02-25 15:37
 */
public class CommitHomeworkServiceImpl implements CommitHomeworkService {

    CommitHomeworkDao commitHomeworkDao = SqlSessionUtil.getSqlSession().getMapper(CommitHomeworkDao.class);

    //TODO
    @Override
    public boolean deleteCommitHomework(CommitHomework ch) throws Exception {
        boolean flag  = false;

        try {

            CommitHomework commitHomework = commitHomeworkDao.getCommitHomeworkByUidAndHid(ch);
            if (commitHomework==null) {
                throw new Exception("删除作业提交失败！");
            }
            int count = commitHomeworkDao.deleteCommitHomework(ch);
            OSSServiceImpl.deleteObject(commitHomework.getFilePath());
            if (count==1) {
                flag = true;
            } else {
                throw new Exception("删除作业提交失败！");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    @Override
    public boolean commitHomework(CommitHomework ch) {
        boolean flag = false;

        try {
            commitHomeworkDao.commitHomework(ch);
            flag = true;
        } catch (Exception e) {
            //说明插入失败，违反主键
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    @Override
    public HashMap<String, Object> getCommitHomeworkByUidAndHid(CommitHomework ch) {

        CommitHomework queryCh = commitHomeworkDao.getCommitHomeworkByUidAndHid(ch);
        HashMap<String, Object> map = new HashMap<>();

        if (queryCh == null) {
            map.put("success",false);
        } else {
            map.put("success",true);
            map.put("commitHomework",queryCh);
        }
        return map;
    }


}
