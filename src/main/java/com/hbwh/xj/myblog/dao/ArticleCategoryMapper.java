package com.hbwh.xj.myblog.dao;

import com.hbwh.xj.myblog.po.Article;
import com.hbwh.xj.myblog.po.ArticleCategory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleCategoryMapper extends Mapper<ArticleCategory> {
    int insertCategories(List<ArticleCategory> categories);
    List<Map> selectStatsByCategory(String userid);
}
