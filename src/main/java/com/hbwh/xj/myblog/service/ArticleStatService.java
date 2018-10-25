package com.hbwh.xj.myblog.service;

public interface ArticleStatService {
    boolean incrementReadCount(String articleId, long delta);
    Long getReadCount(String articleId);
    boolean addArticleItem(Long articleId);
}
