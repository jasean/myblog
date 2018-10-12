package com.hbwh.xj.myblog.dao;

import com.hbwh.xj.myblog.bean.Article;
import com.hbwh.xj.myblog.bean.BlogCatetory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ArticleMapper extends Mapper<Article> {
}