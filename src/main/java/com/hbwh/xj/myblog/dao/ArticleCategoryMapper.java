package com.hbwh.xj.myblog.dao;

import com.hbwh.xj.myblog.po.Article;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ArticleCategoryMapper extends Mapper<Article> {
}
