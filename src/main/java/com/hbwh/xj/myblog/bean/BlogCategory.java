package com.hbwh.xj.myblog.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="t_blog_catetory")
public class BlogCatetory implements Serializable, Cloneable{
    @Id
    private String id;
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BlogCatetory{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
