package com.softi.subwayMap.common.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright:   Copyright 2018 - 2018 chinasofti Tech. Co. Ltd. All Rights Reserved.
 * Date:        2018年3月27日 上午10:58:39
 * Author:      wangx
 * Version:     JTWL_V1.D1.0.0.0
 * Description: redis服务类
 */
//@Service
public class RedisService
{
    

    @Autowired
    private com.softi.subwayMap.common.redis.utils.ClusterRedisTemplate redisTemplate;
    
    /**
     * 设置缓存
     *
     * @param key   缓存key
     * @param value 缓存value
     */
    public String set(String key, String value)
    {
        return redisTemplate.setObj(key, value);
    }
    
    /**
     * 设置缓存，并且自己指定过期时间
     *
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    public String setWithExpireTime(String key, String value, int expireTime)
    {
        return redisTemplate.setObj(key, value, expireTime);
    }
    
    /**
     * 获取指定key的缓存
     *
     * @param key
     */
    public String get(String key)
    {
        return redisTemplate.getObj(key).toString();
    }
    
    /**
     * get String from redis
     * @param key
     * @return
     */
    public String getString(String key)
    {
        return redisTemplate.getString(key);
    }
    
    /**
     * 删除指定key的缓存
     *
     * @param key
     */
    public void delete(String key)
    {
        redisTemplate.delObj(key);
    }
    
    /**
     * 缓存对象
     *
     * @param key
     * @param value
     */
    public void setObj(String key, Object value)
    {
        redisTemplate.setObj(key, value);
    }
    
    /**
     * 获取对象
     *
     * @param key
     * @return
     */
    public Object getObj(String key)
    {
        return redisTemplate.getObj(key);
    }
    
    /**
     * 删除对象
     *
     * @param key
     */
    public void delObj(String key)
    {
        redisTemplate.delObj(key);
    }
}
