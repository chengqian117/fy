package com.softi.subwayMap.common.redis.utils;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

//@Component
public class ClusterRedisTemplate
{
    @Autowired
    private JedisCluster jedisCluster;
    
    @Autowired
    private RedisProperties redisProperties;
    
    /**
     * get value from redis
     * @param key
     * @return
     */
    public byte[] get(byte[] key)
    {
        return jedisCluster.get(key);
    }
    
    /**
     * get value from redis
     * @param key
     * @return
     */
    public Object getObj(String key)
    {
        return SerializeUtils.deserialize(this.get(key.getBytes()));
    }
    /**
     * get String from redis
     * @param key
     * @return
     */
    public String getString(String key)
    {
        return jedisCluster.get(key);
    }
    /**
     * set 
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value)
    {
        return this.set(key, value, getExpire());
    }
    
    /**
     * set 
     * @param key
     * @param value
     * @return
     */
    public String setObj(String key, Object value)
    {
        return this.setObj(key, value, getExpire());
    }
    
    /**
     * set 
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expire)
    {
        jedisCluster.set(key, value);
        if (expire != 0)
        {
            jedisCluster.expire(key, expire);
        }
        return value;
    }
    
    /**
     * set 
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public String setObj(String key, Object value, int expire)
    {
        byte[] strValue = SerializeUtils.serialize(value);
        jedisCluster.set(key.getBytes(), strValue);
        if (expire != 0)
        {
            jedisCluster.expire(key.getBytes(), expire);
        }
        return strValue.toString();
    }
    
    /**
     * del
     * @param key
     */
    public void del(byte[] key)
    {
        jedisCluster.del(key);
    }
    
    /**
     * del
     * @param key
     */
    public void delObj(String key)
    {
        jedisCluster.del(key);
    }
    
    /**
     * flush
     */
    public void flushDB()
    {
        jedisCluster.flushDB();
    }
    
    /**
     * size
     */
    public Long dbSize()
    {
        return jedisCluster.dbSize();
    }
    
    /**
     * keys
     * @param regex
     * @return
     */
    public Set<byte[]> keys(String pattern)
    {
        Set<byte[]> keys = new TreeSet<byte[]>();
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for (String k : clusterNodes.keySet())
        {
            JedisPool jp = clusterNodes.get(k);
            Jedis connection = jp.getResource();
            try
            {
                keys.addAll(connection.keys(pattern.getBytes()));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                connection.close();//用完一定要close这个链接！！！
            }
        }
        return keys;
    }
    
    private int getExpire()
    {
        return redisProperties.getExpire();
    }
}
