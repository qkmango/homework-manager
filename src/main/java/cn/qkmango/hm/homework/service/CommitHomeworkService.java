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

    /**
     * 删除已经提交的作业（用户）
     * @param ch CommitHomework对象，包含主键（uid、hid）
     * @return Map，包含是否成功删除成功的标志位 和 返回给前端的信息
     * @throws Exception
     */
    RespMap<Object> deleteCommitHomework(CommitHomework ch) throws Exception;

    /**
     * 提交作业（用户）
     * @param ch CommitHomework对象，包含此对象的所有信息
     * @return Map，包含是否成功删除成功的标志位 和 返回给前端的信息
     */
    RespMap<Object> commitHomework(CommitHomework ch);
    HashMap<String, Object> getCommitHomeworkByUidAndHid(CommitHomework ch);

}
