package cn.qkmango.hm.homework.domain;

/**
 * @version 1.0
 * <p>Course</p>
 * @className course 科目表对象
 * @author: Mango
 * @date: 2021-02-21 19:02
 */
public class Course {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
