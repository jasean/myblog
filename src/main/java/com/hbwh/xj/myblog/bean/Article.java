package com.hbwh.xj.myblog.bean;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;

public class Article {
    private BigInteger id;
    private String userid;
    private String articleTitle;
    private String articleLabel;
    private String articleCategory;
    private String articlePrivateCategory;
    private Timestamp lastModified;
    private Timestamp createTime;
    private String articleContent;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleLabel() {
        return articleLabel;
    }

    public void setArticleLabel(String articleLabel) {
        this.articleLabel = articleLabel;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public String getArticlePrivateCategory() {
        return articlePrivateCategory;
    }

    public void setArticlePrivateCategory(String articlePrivateCategory) {
        this.articlePrivateCategory = articlePrivateCategory;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleLabel='" + articleLabel + '\'' +
                ", articleCategory='" + articleCategory + '\'' +
                ", articlePrivateCategory='" + articlePrivateCategory + '\'' +
                ", lastModified=" + lastModified +
                ", createTime=" + createTime +
                '}';
    }
}