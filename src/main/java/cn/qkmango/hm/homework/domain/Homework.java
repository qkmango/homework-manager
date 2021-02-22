package cn.qkmango.hm.homework.domain;

/**
 * @version 1.0
 * <p>homework实体类</p>
 * @className homework
 * @author: Mango
 * @date: 2021-02-21 18:35
 */
public class Homework {
    private String id;          //id
    private String title;       //标题
    private String courseId;    //学科
    private String deadline;    //截止日期
    private String briefInfo;   //简略信息
    private String detailInfo;  //详细信息

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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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

    @Override
    public String toString() {
        return "Homework{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", courseId='" + courseId + '\'' +
                ", deadline='" + deadline + '\'' +
                ", briefInfo='" + briefInfo + '\'' +
                ", detailInfo='" + detailInfo + '\'' +
                '}';
    }
}
