package com.hbwh.xj.myblog.dao;

import com.hbwh.xj.myblog.po.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}
