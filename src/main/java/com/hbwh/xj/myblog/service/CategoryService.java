package com.hbwh.xj.myblog.service;

import com.hbwh.xj.myblog.bean.BlogCategory;
import com.hbwh.xj.myblog.bean.PersonalCategory;

import java.util.List;

public interface CategoryService {
    List<BlogCategory> getBlogCategories();
    List<PersonalCategory> getPersonalCatetories(String userid);
}
