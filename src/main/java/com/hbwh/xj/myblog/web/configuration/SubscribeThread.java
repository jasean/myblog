package com.hbwh.xj.myblog.web.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SubscribeThread implements CommandLineRunner {
    private Log log= LogFactory.getLog(SubscribeThread.class);

    @Autowired
    JedisPool jedisPool;

    @Override
    public void run(String... args) throws Exception {
        Jedis jedis= jedisPool.getResource();
        try {
            //监听所有reids通道中的过期事件
            jedis.psubscribe(new OrderSubscribe(), RedisBean.EXPIRED_PATTERN);
        } catch (Exception e) {
            jedis.close();
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }
}
