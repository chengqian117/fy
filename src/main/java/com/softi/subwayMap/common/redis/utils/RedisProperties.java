package com.softi.subwayMap.common.redis.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisProperties
 * @Author wangx
 * @Date 2018/7/5 16:29
 * @Vsersion 1.0
 * @Description redis配置文件
 **/
//@Component
//@ConfigurationProperties(prefix = "spring.redis2")
public class RedisProperties
{
    
    /**数据超时时间**/
    private int expire;
    
    /**连接超时时间*/
    private int timeout = 0;
    
    /**redis集群节点*/
    @Value("${spring.redis2.cluster.nodes}")
    private String nodes;
    
    @Value("${spring.redis2.pool.maxActive}")
    private int maxActive;
    
    @Value("${spring.redis2.pool.maxIdle}")
    private int maxIdle;
    
    @Value("${spring.redis2.pool.maxWait}")
    private int maxWait;
    
    @Value("${spring.redis2.pool.minIdle}")
    private int minIdle;
    
    public int getExpire()
    {
        return expire;
    }
    
    public void setExpire(int expire)
    {
        this.expire = expire;
    }
    
    public int getTimeout()
    {
        return timeout;
    }
    
    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }
    
    public String getNodes()
    {
        return nodes;
    }
    
    public void setNodes(String nodes)
    {
        this.nodes = nodes;
    }
    
    /*public Pool getPool()
    {
        return pool;
    }

    public void setPool(Pool pool)
    {
        this.pool = pool;
    }*/
    public int getMaxActive()
    {
        return maxActive;
    }
    
    public void setMaxActive(int maxActive)
    {
        this.maxActive = maxActive;
    }
    
    public int getMaxIdle()
    {
        return maxIdle;
    }
    
    public void setMaxIdle(int maxIdle)
    {
        this.maxIdle = maxIdle;
    }
    
    public int getMaxWait()
    {
        return maxWait;
    }
    
    public void setMaxWait(int maxWait)
    {
        this.maxWait = maxWait;
    }
    
    public int getMinIdle()
    {
        return minIdle;
    }
    
    public void setMinIdle(int minIdle)
    {
        this.minIdle = minIdle;
    }
}
/*class Pool
{
    *//**最大连接数**/
/*
private int maxActive;

*//**最大空闲连接数**/
/*
private int maxIdle;

*//**获取连接最大等待时间**/
/*
private int maxWait;

*//**最小空闲连接数**/
/*
private int minIdle;

public int getMaxActive()
{
return maxActive;
}

public void setMaxActive(int maxActive)
{
this.maxActive = maxActive;
}

public int getMaxIdle()
{
return maxIdle;
}

public void setMaxIdle(int maxIdle)
{
this.maxIdle = maxIdle;
}

public int getMaxWait()
{
return maxWait;
}

public void setMaxWait(int maxWait)
{
this.maxWait = maxWait;
}

public int getMinIdle()
{
return minIdle;
}

public void setMinIdle(int minIdle)
{
this.minIdle = minIdle;
}
}*/
