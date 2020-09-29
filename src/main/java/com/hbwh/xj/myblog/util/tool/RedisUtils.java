package com.hbwh.xj.myblog.util.tool;

import com.hbwh.xj.myblog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {

    public static final String REDIS_PREFIX = "ARTICLE_";


    @Autowired
    private static RedisService<String, String> redisService;

    @Autowired
    public  void setRedisService(RedisService<String, String> redisService) {
        RedisUtils.redisService = redisService;
    }

    /**
     * redis get
     * @param key
     * @return
     */
    public static String get(String key){
        return redisService.get(key);
    }

    /**
     * redis incr
     * @param key
     * @return
     */
    public static Long incr(String key){
        return redisService.incr(key);
    }

    /**
     * redis remove
     * @param key
     */
    public static void remove(String key){
        redisService.remove(key);
    }
}
