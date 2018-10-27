package com.hbwh.xj.myblog.service;

import com.hbwh.xj.myblog.po.BlogCategory;
import com.hbwh.xj.myblog.po.PersonalCategory;

import java.util.List;

public interface CategoryService {
    List<BlogCategory> getBlogCategories();
    List<PersonalCategory> getPersonalCatetories(String userid);
}
