package com.hbwh.xj.myblog.web.configuration;

import com.hbwh.xj.myblog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {
    @Autowired
    private static RedisService<String, String> redisService;

    @Autowired
    public  void setRedisService(RedisService<String, String> redisService) {
        RedisUtils.redisService = redisService;
    }

    public static String get(String key){
        return redisService.get(key);
    }
}
