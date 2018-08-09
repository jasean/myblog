package com.hbwh.xj.myblog.controller;

import com.hbwh.xj.myblog.bean.User;
import com.hbwh.xj.myblog.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@Api(tags = "博客分类相关API")
public class BlogCategoryController {

    @ApiOperation(value = "获取博客分类", notes = "人工智能/移动开发/物联网/数据库......")
    @GetMapping("/categories")
    public ResponseEntity<Result> getBlogCategories(){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "获取个人文章分类", notes = "自定义分类")
    @GetMapping("/private-categories/{userid}")
    public ResponseEntity<Result> modifyUserData(@PathVariable("userid")String userid){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }
}
