package com.hbwh.xj.myblog.service.impl;

import com.hbwh.xj.myblog.bean.Article;
import com.hbwh.xj.myblog.bean.BlogCategory;
import com.hbwh.xj.myblog.dao.ArticleMapper;
import com.hbwh.xj.myblog.dao.BlogCategoryMapper;
import com.hbwh.xj.myblog.dao.PersonalCategoryMapper;
import com.hbwh.xj.myblog.service.ArticleService;
import com.hbwh.xj.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private PersonalCategoryMapper personalCategoryMapper;

    @Override
    @Transactional
    public boolean publishArticle(Article article) {
        int result = articleMapper.insert(article);
        //插入新的个人分类
        List<String> newCategories = article.getNewArticlePrivateCategory();
        if(newCategories != null || newCategories.size() > 0){
            String userid = article.getUserid();
            BlogCategory blogCategory = null;
            List<BlogCategory> blogCategories = new ArrayList<>(4);
            for(String category: newCategories){
                blogCategory = new BlogCategory(userid, category);
                blogCategories.add(blogCategory);
            }
            result = personalCategoryMapper.insertCategories(blogCategories);
            System.out.println("insertCategories result:" + result);
        }

        return result >= 1;
    }

    @Override
    public List<Article> getArticles(String userid) {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("userid", userid);
        example.orderBy("createTime").desc();
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
