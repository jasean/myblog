package com.hbwh.xj.myblog.dao;

import com.hbwh.xj.myblog.bean.PersonalCatetory;
import com.hbwh.xj.myblog.bean.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PersonalCategoryMapper extends Mapper<PersonalCatetory> {
}