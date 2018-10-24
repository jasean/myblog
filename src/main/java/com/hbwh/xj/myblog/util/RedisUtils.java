package com.hbwh.xj.myblog.util;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

public class RedisUtils {
    @Autowired
    private static JedisPool redisPool;



}
