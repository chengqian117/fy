package com.softi.subwayMap.common.redis.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisClusterConfig
 * @Author wangx
 * @Date 2018/7/5 16:35
 * @Vsersion 1.0
 * @Description 生成JedisCluster对象
 **/
//@Configuration
public class JedisClusterConfig
{
    @Autowired
    private RedisProperties redisProperties;
    
    @Bean(name = "jedisCluster")
    public JedisCluster getJedisCluster()
    {
        String[] serverArray = redisProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String ipPort : serverArray)
        {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(),
                Integer.valueOf(ipPortPair[1].trim())));
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        return new JedisCluster(nodes, redisProperties.getTimeout(),
            jedisPoolConfig);
    }
}
