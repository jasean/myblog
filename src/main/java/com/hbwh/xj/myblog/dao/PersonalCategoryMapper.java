package com.hbwh.xj.myblog.dao;

import com.hbwh.xj.myblog.bean.BlogCategory;
import com.hbwh.xj.myblog.bean.PersonalCategory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PersonalCategoryMapper extends Mapper<PersonalCategory> {
    int insertCategories(List<PersonalCategory> personalCategories);
}
