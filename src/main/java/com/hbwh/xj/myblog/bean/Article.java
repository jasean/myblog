package com.hbwh.xj.myblog.bean;

import com.hbwh.xj.myblog.web.typehandler.JsonTypeHandler;
import org.apache.ibatis.type.StringTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Table(name="t_article")
public class Article implements Serializable, Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String userid;
    private String articleType;
    private String articleTitle;
    private String articleLabel;
    private String articleCategory;

    @ColumnType(typeHandler = JsonTypeHandler.class)
    private Object articlePrivateCategory;

    @Transient
    private List<String> newArticlePrivateCategory;
    private Timestamp lastModified;
    private Timestamp createTime;
    private String articleContent;
    private boolean draft;
    private boolean privacy;

    private Long commentCount;

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

    public Object getArticlePrivateCategory() {
        return articlePrivateCategory;
    }

    public void setArticlePrivateCategory(Object articlePrivateCategory) {
        this.articlePrivateCategory = articlePrivateCategory;
    }

    public List<String> getNewArticlePrivateCategory() {
        return newArticlePrivateCategory;
    }

    public void setNewArticlePrivateCategory(List<String> newArticlePrivateCategory) {
        this.newArticlePrivateCategory = newArticlePrivateCategory;
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

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
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
