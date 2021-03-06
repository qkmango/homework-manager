package cn.qkmango.hm.homework.domain;

/**
 * @version 1.0
 * <p>Format</p>
 * <p>格式化 表对象，代表文件上传是文件名的命名规范</p>
 * @className Format
 * @author: Mango
 * @date: 2021-03-10 11:31
 */
public class Format {

    private int value;
    private String title;
    private int checked;
    private String data;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Format{" +
                "value=" + value +
                ", title='" + title + '\'' +
                ", checked=" + checked +
                ", data='" + data + '\'' +
                '}';
    }
}
