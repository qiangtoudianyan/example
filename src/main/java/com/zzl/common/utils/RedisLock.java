package com.zzl.common.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.List;

/**
 * @author: 张志龙
 * @Date: 2018/7/29 15:43
 * @Description:
 */
public class RedisLock implements AutoCloseable{
    private static final String COMPARE_AND_DELETE =
                                                   "if redis.call('get',KEYS[1]) == ARGV[1]\n"
                                                     + "then\n"
                                                     + "    return redis.call('del',KEYS[1])\n"
                                                     + "else\n"
                                                     + "    return 0\n"
                                                     + "end";
    
    private StringRedisTemplate stringRedisTemplate;
    
    private String key;
    
    private String value;
    
    protected RedisLock(StringRedisTemplate stringRedisTemplate,
                        String key,
                        String value){
        this.stringRedisTemplate = stringRedisTemplate;
        this.key = key;
        this.value = value;
    }
    
    /**
     * 释放redis分布式锁
     */
    private void unlock(){
        List<String> keys = Collections.singletonList(key);
        stringRedisTemplate.execute(new DefaultRedisScript<>(COMPARE_AND_DELETE,
                                                             String.class),
                                    keys,
                                    value);
    }
    
    @Override
    public void close(){
        this.unlock();
    }
}
