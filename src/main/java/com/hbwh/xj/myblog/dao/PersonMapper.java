package com.hbwh.xj.myblog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hbwh.xj.myblog.po.Person;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PersonMapper extends Mapper<Person> {
	public List<Person> getAll();
}
