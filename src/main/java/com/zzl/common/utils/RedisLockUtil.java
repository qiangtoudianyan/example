package com.zzl.common.utils;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * @author: 张志龙
 * @Date: 2018/7/29 15:00
 * @Description:
 */
public class RedisLockUtil{
    private final static String OK = "OK";
    
    public static RedisLock lock(StringRedisTemplate stringRedisTemplate,
                                 final String key,
                                 final String value,
                                 final long expireSeconds){
        String status =
                      stringRedisTemplate.execute((RedisCallback<String>)connection -> {
                          Jedis jedis = (Jedis)connection.getNativeConnection();
                          // nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
                          // expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
                          return jedis.set(key,
                                           value,
                                           "nx",
                                           "ex",
                                           expireSeconds);
                      });
        if(OK.equals(status)){
            return new RedisLock(stringRedisTemplate, key, value);
        }
        return null;
    }
    
}
