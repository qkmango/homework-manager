package cn.qkmango.hm.homework.service.impl;

import cn.qkmango.hm.homework.dao.CommitHomeworkDao;
import cn.qkmango.hm.homework.domain.CommitHomework;
import cn.qkmango.hm.homework.service.CommitHomeworkService;
import cn.qkmango.hm.system.domain.AliOss;
import cn.qkmango.hm.system.service.OSSService;
import cn.qkmango.hm.system.service.impl.OSSServiceImpl;
import cn.qkmango.hm.utils.RespMap;
import cn.qkmango.hm.utils.RespStatusMsg;
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

    @Override
    public HashMap<String, Object> deleteCommitHomework(CommitHomework ch) throws Exception {
        // HashMap<String, Object> map = new HashMap<>();
        RespMap<Object> map = new RespMap<>();

        try {

            CommitHomework commitHomework = commitHomeworkDao.getCommitHomeworkByUidAndHid(ch);
            if (commitHomework==null) {
                throw new Exception(RespStatusMsg.Del_Homework_Fail);
            }
            int count = commitHomeworkDao.deleteCommitHomework(ch);
            OSSServiceImpl.deleteObject(commitHomework.getFilePath());
            if (count==1) {
                map.putSuccess(true);
                map.putMsg(RespStatusMsg.Del_Homework_Success);
            } else {
                map.putSuccess(false);
                map.putMsg(RespStatusMsg.Del_Homework_Fail);
                throw new Exception(RespStatusMsg.Del_Homework_Fail);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e.getCause();
        } finally {
            return map;
        }
    }

    @Override
    public HashMap<String, Object> commitHomework(CommitHomework ch) {

        RespMap<Object> map = new RespMap<>();
        try {
            commitHomeworkDao.commitHomework(ch);
            map.putSuccess(true);
            map.putMsg(RespStatusMsg.Commit_Homework_Success);
        } catch (Exception e) {
            //说明插入失败，违反主键
            map.putSuccess(false);
            map.putMsg(RespStatusMsg.Commit_Homework_Fail);
            e.printStackTrace();
            throw e.getCause();
        } finally {
            return map;
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
