package com.hbwh.xj.myblog.po;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_article_stat")
public class ArticleStat {
    @Id
    private Long articleId;
    private Long readCount;

    public ArticleStat(){}

    public ArticleStat(Long articleId, Long readCount) {
        this.articleId = articleId;
        this.readCount = readCount;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    @Override
    public String toString() {
        return "ArticleStatService{" +
                "articleId=" + articleId +
                ", readCount=" + readCount +
                '}';
    }
}
