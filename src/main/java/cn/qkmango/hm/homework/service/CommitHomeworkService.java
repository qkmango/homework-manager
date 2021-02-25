package cn.qkmango.hm.homework.service;

import cn.qkmango.hm.homework.domain.CommitHomework;

import java.util.HashMap;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className CommitHomeworkService
 * @author: Mango
 * @date: 2021-02-25 15:37
 */
public interface CommitHomeworkService {
    boolean deleteCommitHomework(CommitHomework ch) throws Exception;
    boolean commitHomework(CommitHomework ch);
    HashMap<String, Object> getCommitHomeworkByUidAndHid(CommitHomework ch);

}
