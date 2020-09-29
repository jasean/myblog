package com.hbwh.xj.myblog.service.impl;

import com.hbwh.xj.myblog.dao.ArticleCategoryMapper;
import com.hbwh.xj.myblog.po.Article;
import com.hbwh.xj.myblog.po.ArticleCategory;
import com.hbwh.xj.myblog.po.ArticleStat;
import com.hbwh.xj.myblog.po.PersonalCategory;
import com.hbwh.xj.myblog.dao.ArticleMapper;
import com.hbwh.xj.myblog.dao.ArticleStatMapper;
import com.hbwh.xj.myblog.dao.PersonalCategoryMapper;
import com.hbwh.xj.myblog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    private static final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleStatMapper articleStatMapper;

    @Autowired
    private PersonalCategoryMapper personalCategoryMapper;

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Override
    @Transactional
    public boolean publishArticle(Article article) {
        // 1、插入文章表
        articleMapper.insert(article);

        //2、插入访问量表
        articleStatMapper.insertSelective(new ArticleStat(article.getId().longValue(), 0L));

        //3、插入文章分类表
        List<ArticleCategory> categories = getArticleCategories(article);
        articleCategoryMapper.insertCategories(categories);

        //4、插入新的个人分类
        List<String> newCategories = article.getNewArticlePrivateCategory();
        log.info("insert new category: {}",newCategories);
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
        if(!"readCount".equals(order)){
            Example example = new Example(Article.class);
            example.createCriteria().andEqualTo("userid", userid)
                .andEqualTo("status", 0);
            example.orderBy(order).desc();
            return articleMapper.selectByExample(example);
        }else{
            return articleMapper.selectArticlesByReadCount(userid);
        }
    }

    @Override
    public List<Article> getAllArticles(String userid, boolean containTrash) {
        Example example = new Example(Article.class);
        Example.Criteria criteria =  example.createCriteria().andEqualTo("userid", userid);
        if(!containTrash){
            criteria.andNotEqualTo("status", 3);
        }
        example.orderBy("createTime").desc();
        return articleMapper.selectByExample(example);
    }

    @Override
    public Article getArticle(String articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    @Override
    @Transactional
    public boolean deleteArticle(Long id) {
        articleMapper.deleteByPrimaryKey(id);
        articleCategoryMapper.delete(new ArticleCategory(BigInteger.valueOf(id), null, null));
        return true;
    }

    @Override
    @Transactional
    public boolean modifyArticle(Article article) {
        //1、更新文章表
        articleMapper.updateByPrimaryKeySelective(article);

        //2、更新文章分类表,首先删除文章所有分类，再插入
        articleCategoryMapper.delete(new ArticleCategory(article.getId(), null, null));
        articleCategoryMapper.insertCategories(getArticleCategories(article));

        return true;
    }

    @Override
    public List<Map> getStatsByCategory(String userid) {
        return articleCategoryMapper.selectStatsByCategory(userid);
    }

    @Override
    public List<Map> getStatsByDate(String userid) {
        return articleMapper.selectStatsByCreateDate(userid);
    }

    @Override
    public List<Map> getStatsByStatus(String userid) {
        return articleMapper.selectStatsByStatus(userid);
    }

    /**
     * 获取持久层对象
     * @param article
     * @return
     */
    private List<ArticleCategory> getArticleCategories(Article article){
        List<ArticleCategory> cates = new ArrayList<>(8);
        List<String> categories = (List<String>) article.getArticlePrivateCategory();
        ArticleCategory articleCategory = null;
        for(String category: categories){
            articleCategory = new ArticleCategory(article.getId(), category, article.getUserid());
            cates.add(articleCategory);
        }

        return cates;
    }
}
