package com.hbwh.xj.myblog.service.impl;

import com.hbwh.xj.myblog.bean.BlogCategory;
import com.hbwh.xj.myblog.bean.PersonalCategory;
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
    public List<BlogCategory> getBlogCategories() {
        return blogCategoryMapper.selectAll();
    }

    @Override
    public List<PersonalCategory> getPersonalCatetories(String userid) {
        Example example = new Example(PersonalCategory.class);
        example.createCriteria().andEqualTo("userid", userid);
        return personalCategoryMapper.selectByExample(example);
    }

}
