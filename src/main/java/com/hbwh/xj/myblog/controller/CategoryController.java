package com.hbwh.xj.myblog.controller;

import com.hbwh.xj.myblog.po.BlogCategory;
import com.hbwh.xj.myblog.po.PersonalCategory;
import com.hbwh.xj.myblog.service.CategoryService;
import com.hbwh.xj.myblog.util.result.ResponseResult;
import com.hbwh.xj.myblog.util.result.Result;
import com.hbwh.xj.myblog.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogs")
@Api(tags = "博客分类相关API")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取博客分类", notes = "人工智能/移动开发/物联网/数据库......")
    @GetMapping("/categories")
    public ResponseEntity<Result> getBlogCategories(){
        List<BlogCategory> categories = categoryService.getBlogCategories();
        return ResponseResult.get().data(categories).resultCode(ResultCode.SUCCESS).build();
    }

    @ApiOperation(value = "获取个人文章分类", notes = "自定义分类")
    @GetMapping("/personal-categories/{userid}")
    public ResponseEntity<Result> modifyUserData(@PathVariable("userid")String userid){
        List<PersonalCategory> categories = categoryService.getPersonalCatetories(userid);
        List<String> cates = categories.stream().map(PersonalCategory::getCategory)
                .collect(Collectors.toList());
        return ResponseResult.get().data(cates).resultCode(ResultCode.SUCCESS).build();
    }
}
