package com.hbwh.xj.myblog.service.impl;

import com.hbwh.xj.myblog.bean.ArticleStat;
import com.hbwh.xj.myblog.dao.ArticleStatMapper;
import com.hbwh.xj.myblog.service.ArticleStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articleStatService")
public class ArticleStatServiceImpl implements ArticleStatService {

    private ArticleStatMapper articleStatMapper;

    public ArticleStatMapper getArticleStatMapper() {
        return articleStatMapper;
    }

    @Autowired
    public void setArticleStatMapper(ArticleStatMapper articleStatMapper) {
        this.articleStatMapper = articleStatMapper;
    }


    @Override
    public boolean incrementReadCount(String articleId, long delta) {
        ArticleStat articleStat = articleStatMapper.selectByPrimaryKey(articleId);
        articleStat.setReadCount(articleStat.getReadCount() + delta);
        return articleStatMapper.updateByPrimaryKey(articleStat) == 1;
    }

    @Override
    public Long getReadCount(String articleId) {
        ArticleStat articleStat = articleStatMapper.selectByPrimaryKey(articleId);
        return articleStat.getReadCount();
    }

    @Override
    public boolean addArticleItem(Long articleId) {
        return articleStatMapper.insertSelective(
                new ArticleStat(articleId, 0L)
        ) == 1;
    }
}
