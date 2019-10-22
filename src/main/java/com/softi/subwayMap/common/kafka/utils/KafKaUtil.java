package com.softi.subwayMap.common.kafka.utils;

import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softi.subwayMap.common.util.Constants;

public class KafKaUtil {
	private final static Logger logger=LoggerFactory.getLogger("KafKaUtil");
	
	
	
	public static KafkaProducer<String, String> instanceProducer(){
		
		    Properties properties = new Properties();
	        properties.put("bootstrap.servers",  Constants.KAFKA_BOOTSTRAP_SERVERS);
	        properties.put("acks", "all");
	        properties.put("retries", 0);
	        properties.put("batch.size", 16384);
	        properties.put("linger.ms", 1);
	        properties.put("buffer.memory", 33554432);
	        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        KafkaProducer<String, String> producer=null;
	        try{
	            producer = new KafkaProducer<String, String>(properties);
	        }catch(Exception e){
	        	e.printStackTrace();
	        	logger.error(Constants.ERROR_KAFKAPRO_EXCEP);
	        	//throw new NullPointerException();
	        }
		return producer;
	}
	
	public static KafkaConsumer<String, String> instanceConsumer(){
			KafkaConsumer<String, String> consumer=null;
			Properties props = new Properties();
	        props.put("bootstrap.servers", Constants.KAFKA_BOOTSTRAP_SERVERS);
	        //每个消费者分配独立的组号
	        props.put("group.id", "test2");
	        //如果value合法，则自动提交偏移量
	        props.put("enable.auto.commit", "true");
	        //设置多久一次更新被消费消息的偏移量
	        props.put("auto.commit.interval.ms", "1000");
	        //设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
	        props.put("session.timeout.ms", "30000");
	        //自动重置offset
	        props.put("auto.offset.reset","earliest");
	        props.put("key.deserializer",
	                "org.apache.kafka.common.serialization.StringDeserializer");
	        props.put("value.deserializer",
	                "org.apache.kafka.common.serialization.StringDeserializer");
	        try {
	        	consumer = new KafkaConsumer<String, String>(props);
	        	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error(Constants.ERROR_KAFKACON_EXCEP);
			}
	        
	        return consumer;
	}
	public static ConsumerConnector instanceConsumerNew(){
		ConsumerConnector consumer=null;
		Properties props = new Properties();
		props.put("zookeeper.connect", "192.168.50.23:2181");
        props.put("bootstrap.servers", Constants.KAFKA_BOOTSTRAP_SERVERS);
        //每个消费者分配独立的组号
        props.put("group.id", "test2");
        //如果value合法，则自动提交偏移量
        props.put("enable.auto.commit", "true");
        //设置多久一次更新被消费消息的偏移量
        props.put("auto.commit.interval.ms", "1000");
        //设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
        props.put("session.timeout.ms", "30000");
        //自动重置offset
        props.put("auto.offset.reset","smallest");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        try {
        	ConsumerConfig ConsumerConfig=new ConsumerConfig(props);
        	consumer = Consumer.createJavaConsumerConnector(ConsumerConfig);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error(Constants.ERROR_KAFKACON_EXCEP+"new");
		}
        
        return consumer;
}
	
}
