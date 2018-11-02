package com.hbwh.xj.myblog.web.redis;

import com.hbwh.xj.myblog.util.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.JedisPubSub;

public class OrderSubscribe extends JedisPubSub {

    private static final Log log = LogFactory.getLog(OrderSubscribe.class);

    public void onPSubscribe(String pattern, int subscribedChannels) {
        log.error("Subscribe-onPSubscribe>>>>>>>>>>>>>>>>>>>>>>>>"+pattern + "=" + subscribedChannels);
    }

    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("pattern:%s,channel:%s,message:%s");
        log.error(pattern + "=" + channel + "=" + message);
        if ("__keyevent@0__:expired".equals(channel)) {
//            log.info(redisService.get(message));
            log.info(RedisUtils.get(message));
        }
    }
}
