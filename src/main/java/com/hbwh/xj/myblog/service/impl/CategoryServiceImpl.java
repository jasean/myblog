package com.hbwh.xj.myblog.service.impl;

import com.hbwh.xj.myblog.bean.BlogCatetory;
import com.hbwh.xj.myblog.bean.PersonalCatetory;
import com.hbwh.xj.myblog.dao.BlogCategoryMapper;
import com.hbwh.xj.myblog.dao.PersonalCategoryMapper;
import com.hbwh.xj.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private PersonalCategoryMapper personalCategoryMapper;

    @Override
    public List<BlogCatetory> getBlogCategories() {
        return blogCategoryMapper.selectAll();
    }

    @Override
    public List<PersonalCatetory> getPersonalCatetories(String userid) {
        Example example = new Example(PersonalCatetory.class);
        example.createCriteria().andEqualTo("userid", userid);
        return personalCategoryMapper.selectByExample(example);
    }
}
