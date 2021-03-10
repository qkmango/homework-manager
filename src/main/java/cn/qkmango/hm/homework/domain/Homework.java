package cn.qkmango.hm.homework.domain;

/**
 * @version 1.0
 * <p>homework</p>
 * @className homework 表对象
 * @author: Mango
 * @date: 2021-02-21 18:35
 */
public class Homework {
    private String id;              //id
    private String title;           //标题
    private String course;          //学科（上传作业时为学科id，获取作业时为学科名）
    private String lastCommitDate;  //截止日期
    private String createDate;      //创建日期
    private String briefInfo;       //简略信息
    private String detailInfo;      //详细信息
    private String format;          //作业提交格式

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getLastCommitDate() {
        return lastCommitDate;
    }

    public void setLastCommitDate(String lastCommitDate) {
        this.lastCommitDate = lastCommitDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", course='" + course + '\'' +
                ", lastCommitDate='" + lastCommitDate + '\'' +
                ", createDate='" + createDate + '\'' +
                ", briefInfo='" + briefInfo + '\'' +
                ", detailInfo='" + detailInfo + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
