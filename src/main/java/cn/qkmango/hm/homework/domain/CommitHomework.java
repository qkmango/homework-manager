package cn.qkmango.hm.homework.domain;

/**
 * @version 1.0
 * <p>CommitHomework</p>
 * <p>commit_homework 表对象</p>
 * @className CommitHomework
 * @author: Mango
 * @date: 2021-02-25 14:16
 */
public class CommitHomework {
    private String uid;
    private String hid;
    private String filePath;
    private String datetime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "CommitHomework{" +
                "uid='" + uid + '\'' +
                ", hid='" + hid + '\'' +
                ", filePath='" + filePath + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
