package com.hbwh.xj.myblog.service;

import com.hbwh.xj.myblog.po.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    boolean publishArticle(Article article);
    /** 默认按创建时间排序*/
    List<Article> getArticles(String userid, String order);
    /** 按个人分类 分组*/
    List<Article> getArticlesGrouped(String userid);
    Article getArticle(String articleId);
    boolean deleteArticle(Long id);
    boolean modifyArticle(Article article);
    /** 查询统计信息，按个人分类*/
    List<Map> getStatsByCategory(String userid);
    /** 查询统计信息，按年月*/
    List<Map> getStatsByDate(String userid);
    /** 查询统计各个状态的文章数量*/
    List<Map> getStatsByStatus(String userid);
}
