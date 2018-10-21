package com.hbwh.xj.myblog.controller;

import com.hbwh.xj.myblog.bean.Article;
import com.hbwh.xj.myblog.service.ArticleService;
import com.hbwh.xj.myblog.util.result.ResponseResult;
import com.hbwh.xj.myblog.util.result.Result;
import com.hbwh.xj.myblog.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@Api(tags = "博客文章相关API")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @ApiOperation(value = "获取个人文章列表", notes = "")
    @GetMapping("/{userid}")
    public ResponseEntity<Result> getArticles(
            @PathVariable("userid")String userid,
            @RequestParam("orderBy")String order){
        List<Article> articles =  articleService.getArticles(userid, order);
        return ResponseResult.get().data(articles).resultCode(ResultCode.SUCCESS).build();
    }

    //TODO 是否需要这个接口？
    @ApiOperation(value = "获取指定博客信息", notes = "")
    @GetMapping("/{userid}/{id}")
    public ResponseEntity<Result> getArticle(@PathVariable("userid")String userid,
                                             @PathVariable("id")String articleid){
        Article article = articleService.getArticle(articleid);
        return ResponseResult.get().data(article).resultCode(ResultCode.SUCCESS).build();
    }

    @ApiOperation(value = "发布博客", notes = "")
    @PostMapping("/{userid}")
    public ResponseEntity<Result> publishArticle(@PathVariable("userid")String userid,
                                                 Article article){

        articleService.publishArticle(article);
        return ResponseResult.get().resultCode(ResultCode.SUCCESS).build();
    }

    @ApiOperation(value = "修改博客", notes = "")
    @PutMapping("/{userid}")
    public ResponseEntity<Result> modifyArticle(@PathVariable("userid")String userid,
                                                 Article article){

        articleService.modifyArticle(article);
        return ResponseResult.get().resultCode(ResultCode.SUCCESS).build();
    }

    @ApiOperation(value = "删除博客", notes = "")
    @DeleteMapping("/{userid}/{id}")
    public ResponseEntity<Result> deleteArticle(@PathVariable("userid")String userid,
                                                @PathVariable("id")Long id){

        articleService.deleteArticle(id.toString());
        return ResponseResult.get().resultCode(ResultCode.SUCCESS).build();
    }

}
