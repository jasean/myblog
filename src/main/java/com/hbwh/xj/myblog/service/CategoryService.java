package com.hbwh.xj.myblog.service;

import com.hbwh.xj.myblog.bean.BlogCatetory;
import com.hbwh.xj.myblog.bean.PersonalCatetory;
import com.hbwh.xj.myblog.bean.User;

import java.util.List;

public interface CategoryService {
    List<BlogCatetory> getBlogCategories();
    List<PersonalCatetory> getPersonalCatetories(String userid);
}
