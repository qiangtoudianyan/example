package com.zzl.common.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import org.springframework.data.redis.connection.RedisPassword;

/**
 * @author: xhyzzzl
 * @Date: 2018/7/14 19:58
 * @Description: 配置类
 */
public class ConfigBeanUtil {
    /**
     * 返回RedisPassword
     * 
     * @param redisPublicKey
     *            公匙
     * @param redisPassword
     *            密码
     * @return RedisPassword
     * @throws Exception
     */
    public static RedisPassword getRedisPassword(String redisPublicKey,
                                                 String redisPassword) throws Exception{
        return RedisPassword.of(ConfigTools.decrypt(redisPublicKey,
                                                    redisPassword));
    }
}
