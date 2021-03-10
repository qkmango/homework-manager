package cn.qkmango.hm.utils;

/**
 * @version 1.0
 * <p>响应信息</p>
 * <p>通过调用不同常量，返回相应的状态信息msg</p>
 * @className RespStatusMsg
 * @author: Mango
 * @date: 2021-03-04 09:23
 */
public class RespStatusMsg {
    public static final String Not_Logged_In = "您未登陆，请登陆";
    public static final String Insufficient_Permissions = "权限不足";

    public static final String Homework_Add_Fail = "homework 添加失败";
    public static final String Homework_Add_Success = "homework 添加成功";

    public static final String Commit_Homework_Success = "homework 提交成功";
    public static final String Commit_Homework_Fail = "homework 提交失败";

    public static final String Edit_Homework_Success = "编辑 homework 保存成功";
    public static final String Edit_Homework_Fail = "编辑 homework 保存失败";

    public static final String Del_Homework_Success = "删除 homework 保存成功";
    public static final String Del_Homework_Fail = "删除 homework 保存失败";

    public static final String Del_CommitHomework_Success = "删除 已经提交的 homework 成功";
    public static final String Del_CommitHomework_Fail = "删除 已经提交的 homework 失败";

    public static final String Get_Homework_Success = "通过 ID 过去 homework 详情成功";
    public static final String Get_Homework_Fail = "通过 ID 过去 homework 详情失败";

    public static final String Change_Userinfo_Success = "修改用户信息成功";
    public static final String Change_Userinfo_Fail = "修改用户信息失败";

}