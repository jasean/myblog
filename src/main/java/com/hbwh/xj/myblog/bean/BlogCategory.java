package com.hbwh.xj.myblog.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="t_blog_category")
public class BlogCategory implements Serializable, Cloneable{
    @Id
    private String id;
    private String category;

    public BlogCategory() {
    }

    public BlogCategory(String id, String category) {
        this.id = id;
        this.category = category;
    }

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
        return "BlogCategory{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
