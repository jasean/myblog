package com.hbwh.xj.myblog.service;

import com.hbwh.xj.myblog.bean.Article;
import com.hbwh.xj.myblog.bean.BlogCatetory;
import com.hbwh.xj.myblog.bean.PersonalCatetory;

import java.util.List;

public interface ArticleService {
    boolean publishArticle(Article article);
    List<Article> getArticles(String userid);
    List<Article> getArticlesGrouped(String userid);
    boolean deleteArticle(String id);
    boolean modifyArticle(Article article);
}
