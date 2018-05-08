package cn.pomelo.dal.mysql.object;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 团伙节点 Created by zhengyong on 16/8/19.
 */
public class UserDO implements Serializable {

    private static final long serialVersionUID = 752128156517363861L;

    private Long              id;
    private String            name;
    private Integer           age;
    private Timestamp         gmtCreate;
    private Timestamp         gmtModified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

}
