package com.softi.subwayMap.common.redis.utils;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.softi.subwayMap.common.util.AppProperties;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	
	public static JedisCluster instanse(){
		Properties pro=AppProperties.getProper();
		String HOST=pro.getProperty("HOST");
		int MaxIdle=Integer.parseInt(pro.getProperty("MaxIdle"));
		int MaxWait=Integer.parseInt(pro.getProperty("MaxWait"));
		int Timeout=Integer.parseInt(pro.getProperty("Timeout"));
		  String[] serverArray =HOST.split(",");
	        Set<HostAndPort> nodes = new HashSet<>();
	        for (String ipPort : serverArray)
	        {
	            String[] ipPortPair = ipPort.split(":");
	            nodes.add(new HostAndPort(ipPortPair[0].trim(),
	                Integer.valueOf(ipPortPair[1].trim())));
	        }
	        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	        jedisPoolConfig.setMaxIdle(MaxIdle);
	        jedisPoolConfig.setMaxWaitMillis(MaxWait);
	        JedisCluster jedis= new JedisCluster(nodes, Timeout, jedisPoolConfig);
	       // return new JedisCluster(nodes, Timeout, 1000, 100, password, jedisPoolConfig);
	        return jedis;
	
	}
	
	public static JedisPool instanseLone(){
		//Properties pro=SparkProperties.getProper();
		//String HOST="192.168.50.21:6379";
		int MaxIdle=8;
		int MaxWait=3000;
		int Timeout=5000;
	    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	    jedisPoolConfig.setMaxIdle(MaxIdle);
	    jedisPoolConfig.setMaxWaitMillis(MaxWait);
	       // return new JedisCluster(nodes, Timeout, 1000, 100, password, jedisPoolConfig);
	    JedisPool pool=new JedisPool(jedisPoolConfig,"114.116.99.26",6379,Timeout,"flush");
	    return pool;
	
	}
	/*
	public static void main(String[] args) {
		JedisPool pool= RedisUtil.instanseLone();
		Jedis jedis=pool.getResource();
		System.out.println(jedis.get("asdas"));
		jedis.close();
		pool.close();
	}
	*/
   
   
}
