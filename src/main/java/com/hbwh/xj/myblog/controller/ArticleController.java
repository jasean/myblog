package com.hbwh.xj.myblog.controller;

import com.hbwh.xj.myblog.po.Article;
import com.hbwh.xj.myblog.service.ArticleService;
import com.hbwh.xj.myblog.service.ArticleStatService;
import com.hbwh.xj.myblog.util.RedisUtils;
import com.hbwh.xj.myblog.util.result.ResponseResult;
import com.hbwh.xj.myblog.util.result.Result;
import com.hbwh.xj.myblog.util.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
@Api(tags = "博客文章相关API")
public class ArticleController {

    /** 缓存的文章访问量阈值 */
    private static final long THRESHOLD = 5;
    private static Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleStatService articleStatService;

    @ApiOperation(value = "获取个人分类维度的统计信息", notes = "")
    @GetMapping("/stats/category/{userid}")
    public ResponseEntity<Result> getStatsByCategory(@PathVariable("userid")String userid){
        List<Map> stats = articleService.getStatsByCategory(userid);
        return ResponseResult.get().data(stats).resultCode(ResultCode.SUCCESS).build();
    }

    @ApiOperation(value = "获取年月维度的统计信息", notes = "")
    @GetMapping("/stats/date/{userid}")
    public ResponseEntity<Result> getStatsByDate(@PathVariable("userid")String userid){
        List<Map> stats = articleService.getStatsByDate(userid);
        return ResponseResult.get().data(stats).resultCode(ResultCode.SUCCESS).build();
    }

    @ApiOperation(value = "获取个人文章列表", notes = "")
    @GetMapping("/{userid}")
    public ResponseEntity<Result> getArticles(
            @PathVariable("userid")String userid,
            @RequestParam("orderBy")String order){
        List<Article> articles =  articleService.getArticles(userid, order);
        return ResponseResult.get().data(articles).resultCode(ResultCode.SUCCESS).build();
    }

    @ApiOperation(value = "获取指定博客信息", notes = "")
    @GetMapping("/{userid}/{id}")
    public ResponseEntity<Result> getArticle(@PathVariable("userid")String userid,
                                             @PathVariable("id")String articleid){
        Article article = articleService.getArticle(articleid);

        String redisKey = RedisUtils.REDIS_PREFIX + articleid;
        synchronized (this){
            long count = RedisUtils.incr(redisKey);
            log.info("----" + count);
            if(count == THRESHOLD){
                RedisUtils.remove(redisKey);
                //TODO FIXME 可以异步执行，尽量减少锁的占用
                articleStatService.incrementReadCount(articleid, count);
            }
        }

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

        articleService.deleteArticle(id);
        return ResponseResult.get().resultCode(ResultCode.SUCCESS).build();
    }

    @Deprecated
    @ApiOperation(value = "增加博客阅读数", notes = "")
    @PutMapping("/{userid}/{id}")
    public ResponseEntity<Result> updateReadCount(@PathVariable("userid")String userid,
                                                @PathVariable("id")Long id){
        //TODO 如何高效的更新阅读量字段呢？如果直接在方法上加上锁，肯定低效！
        /**
         *  可以通过redis缓存计数
         *  1）为缓存设置过期时间，监听过期事件，在事件里更新到数据表，但过期事件里无法获得key对应的值；
         *  2）周期性的更新到数据库或缓存达到一个阈值后更新；
         *  3）通过分析日志，周期性的更新到数据库；
         * */

        return ResponseResult.get().resultCode(ResultCode.SUCCESS).build();
    }

}
