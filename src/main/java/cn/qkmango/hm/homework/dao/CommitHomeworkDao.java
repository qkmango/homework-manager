package cn.qkmango.hm.homework.dao;

import cn.qkmango.hm.homework.domain.CommitHomework;

/**
 * @version 1.0
 * @Description: //TODO
 * <p>类简介</p>
 * <p>类详细介绍</p>
 * @className CommitHomeworkDao
 * @author: Mango
 * @date: 2021-02-25 15:40
 */
public interface CommitHomeworkDao {
    int deleteCommitHomework(CommitHomework ch);
    void commitHomework(CommitHomework ch);

    CommitHomework getCommitHomeworkByUidAndHid(CommitHomework ch);
}
