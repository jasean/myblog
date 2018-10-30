package com.hbwh.xj.myblog.dao;

import com.hbwh.xj.myblog.po.Article;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleMapper extends Mapper<Article> {
    /** 根据创建年月，查询统计计数*/
    List<Map> selectStatsByCreateDate(String userid);
    List<Article> selectArticlesByReadCount(String userid);
    List<Map> selectStatsByStatus(String userid);
}
