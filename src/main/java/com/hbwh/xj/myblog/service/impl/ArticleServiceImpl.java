package com.hbwh.xj.myblog.service.impl;

import com.hbwh.xj.myblog.bean.Article;
import com.hbwh.xj.myblog.dao.ArticleMapper;
import com.hbwh.xj.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean publishArticle(Article article) {
        int result = articleMapper.insert(article);
        return result == 1;
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
