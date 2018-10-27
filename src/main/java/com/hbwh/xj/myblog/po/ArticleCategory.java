package com.hbwh.xj.myblog.po;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Table(name = "t_article_category")
public class ArticleCategory {
    @Id
    private BigInteger articleId;
    private String category;
    private String userid;

    public ArticleCategory() {
    }

    public ArticleCategory(BigInteger articleId, String category, String userid) {
        this.articleId = articleId;
        this.category = category;
        this.userid = userid;
    }

    public BigInteger getArticleId() {
        return articleId;
    }

    public void setArticleId(BigInteger articleId) {
        this.articleId = articleId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "ArticleCategory{" +
                "articleId=" + articleId +
                ", category='" + category + '\'' +
                '}';
    }
}
