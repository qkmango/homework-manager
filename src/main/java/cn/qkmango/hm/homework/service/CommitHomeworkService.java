package cn.qkmango.hm.homework.service;

import cn.qkmango.hm.homework.domain.CommitHomework;
import cn.qkmango.hm.utils.RespMap;

import java.util.HashMap;

/**
 * @version 1.0
 * <p>CommitHomeworkService</p>
 * <p>作业提交服务</p>
 * @className CommitHomeworkService
 * @author: Mango
 * @date: 2021-02-25 15:37
 */
public interface CommitHomeworkService {
    RespMap<Object> deleteCommitHomework(CommitHomework ch) throws Exception;
    RespMap<Object> commitHomework(CommitHomework ch);
    HashMap<String, Object> getCommitHomeworkByUidAndHid(CommitHomework ch);

}
