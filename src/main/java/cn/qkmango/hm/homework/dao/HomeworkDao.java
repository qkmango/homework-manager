package cn.qkmango.hm.homework.dao;

import cn.qkmango.hm.homework.domain.CommitHomework;
import cn.qkmango.hm.homework.domain.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * <p>HomeworkDao</p>
 * <p>homework 表的数据访问层</p>
 * @className HomeworkDao
 * @author: Mango
 * @date: 2021-02-21 19:00
 */
public interface HomeworkDao {
    int addHomeWork(Homework homework);

    Homework getHomeworkById(String id);

    List<Homework> getHomeworkPageList(Map<String, Object> map);

    int getTotalByCondition();

    int getHomeworkIsCommit(@Param("uid") String uid, @Param("hid") String hid);

    int deleteHomework(String hid);

    Homework getHomeworkByIdOfEdit(String hid);

    int editHomework(Homework homework);

    List<HashMap<String, Object>> getHomeworkAndCommitCountPageList(Map<String, Object> map);
}
