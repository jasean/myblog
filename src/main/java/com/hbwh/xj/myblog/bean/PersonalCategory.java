package com.hbwh.xj.myblog.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="t_private_category")
public class PersonalCategory implements Serializable, Cloneable{
    @Id
    private String userid;
    @Id
    private String category;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "PersonalCategory{" +
                "userid='" + userid + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
