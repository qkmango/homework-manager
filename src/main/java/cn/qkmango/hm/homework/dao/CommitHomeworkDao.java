package cn.qkmango.hm.homework.dao;

import cn.qkmango.hm.homework.domain.CommitHomework;

/**
 * @version 1.0
 * <p>CommitHomeworkDao</p>
 * <p>commit_homework 表的数据访问层</p>
 * @className CommitHomeworkDao
 * @author: Mango
 * @date: 2021-02-25 15:40
 */
public interface CommitHomeworkDao {
    int deleteCommitHomework(CommitHomework ch);
    void commitHomework(CommitHomework ch);
    CommitHomework getCommitHomeworkByUidAndHid(CommitHomework ch);
}
