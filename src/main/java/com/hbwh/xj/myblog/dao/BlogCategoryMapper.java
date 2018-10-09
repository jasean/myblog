package com.hbwh.xj.myblog.dao;

import com.hbwh.xj.myblog.bean.BlogCatetory;
import com.hbwh.xj.myblog.bean.PersonalCatetory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BlogCategoryMapper extends Mapper<BlogCatetory> {
}
