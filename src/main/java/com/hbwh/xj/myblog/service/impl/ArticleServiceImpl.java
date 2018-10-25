package com.hbwh.xj.myblog.service.impl;

import com.hbwh.xj.myblog.bean.Article;
import com.hbwh.xj.myblog.bean.ArticleStat;
import com.hbwh.xj.myblog.bean.BlogCategory;
import com.hbwh.xj.myblog.bean.PersonalCategory;
import com.hbwh.xj.myblog.dao.ArticleMapper;
import com.hbwh.xj.myblog.dao.ArticleStatMapper;
import com.hbwh.xj.myblog.dao.BlogCategoryMapper;
import com.hbwh.xj.myblog.dao.PersonalCategoryMapper;
import com.hbwh.xj.myblog.service.ArticleService;
import com.hbwh.xj.myblog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    private static final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleStatMapper articleStatMapper;

    @Autowired
    private PersonalCategoryMapper personalCategoryMapper;

    @Override
    @Transactional
    public boolean publishArticle(Article article) {
        articleMapper.insert(article);
        log.info("----insert return key:", article);
        articleStatMapper.insertSelective(new ArticleStat(article.getId().longValue(), 0L));
        //插入新的个人分类
        List<String> newCategories = article.getNewArticlePrivateCategory();
        System.out.println(newCategories);
        if(newCategories != null && newCategories.size() > 0){
            String userid = article.getUserid();
            PersonalCategory personalCategory = null;
            List<PersonalCategory> personalCategories = new ArrayList<>(4);
            for(String category: newCategories){
                personalCategory = new PersonalCategory(userid, category);
                personalCategories.add(personalCategory);
            }
            personalCategoryMapper.insertCategories(personalCategories);
        }

        return true;
    }

    @Override
    public List<Article> getArticles(String userid, String order) {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("userid", userid);
        example.orderBy(order).desc();
        return articleMapper.selectByExample(example);
    }

    @Override
    public List<Article> getArticlesGrouped(String userid) {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("userid", userid);
        example.orderBy("articlePrivateCategory").asc();
        return articleMapper.selectByExample(example);
    }

    @Override
    public Article getArticle(String articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public boolean deleteArticle(String id) {
        int result = articleMapper.deleteByPrimaryKey(id);
        return result == 1;
    }

    @Override
    public boolean modifyArticle(Article article) {
        int result = articleMapper.updateByPrimaryKeySelective(article);
        return result == 1;
    }
}
