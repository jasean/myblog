package com.hbwh.xj.myblog.controller;

import com.hbwh.xj.myblog.bean.Article;
import com.hbwh.xj.myblog.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@Api(tags = "博客文章相关API")
public class ArticleController {

    @ApiOperation(value = "获取个人文章列表", notes = "")
    @GetMapping("/{userid}")
    public ResponseEntity<Result> getArticles(@PathVariable("userid")String userid){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "获取指定博客信息", notes = "")
    @GetMapping("/{userid}/{id}")
    public ResponseEntity<Result> getArticle(@PathVariable("userid")String userid,
                                             @PathVariable("id")String articleid){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "发布博客", notes = "")
    @PostMapping("/{userid}")
    public ResponseEntity<Result> publishArticle(@PathVariable("userid")String userid,
                                                 Article article){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "修改博客", notes = "")
    @PutMapping("/{userid}")
    public ResponseEntity<Result> modifyArticle(@PathVariable("userid")String userid,
                                                 Article article){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

    @ApiOperation(value = "删除博客", notes = "")
    @DeleteMapping("/{userid}/{id}")
    public ResponseEntity<Result> deleteArticle(@PathVariable("userid")String userid,
                                                @PathVariable("id")Long id){

        return new ResponseEntity(Result.success(), HttpStatus.OK);
    }

}
