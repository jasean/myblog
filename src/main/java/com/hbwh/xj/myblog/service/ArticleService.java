package com.hbwh.xj.myblog.service;

import com.hbwh.xj.myblog.bean.Article;

import java.util.List;

public interface ArticleService {
    boolean publishArticle(Article article);
    /** 默认按创建时间排序*/
    List<Article> getArticles(String userid);
    /** 按个人分类 分组*/
    List<Article> getArticlesGrouped(String userid);
    boolean deleteArticle(String id);
    boolean modifyArticle(Article article);
}
