package com.softi.subwayMap.common.kafka.service;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.softi.subwayMap.common.kafka.utils.KafKaUtil;

@Service
public class KafkaService {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());

	private KafkaProducer<String, String> producer;
	
	private KafkaConsumer<String, String> consumer;
	
	private final int CONSUMER_LOOP_NUMBER=10;

	public KafkaProducer<String, String> getProducer() {
		return producer;
	}

	public void setProducer(KafkaProducer<String, String> producer) {
		this.producer = producer;
	}

	public KafkaConsumer<String, String> getConsumer() {
		return consumer;
	}

	public void setConsumer(KafkaConsumer<String, String> consumer) {
		this.consumer = consumer;
	}
	/**
	 * 
	 * @param topic 
	 * @param key  
	 * @param message 信息
	 * @return int  当结果为0时，说明kafka连接异常，考虑数据做其他处理
	 */
	public int senMessage(String topic,String key,String ...message){
		if(null!=message&&message.length>0){
			if(null==this.producer){
				this.producer=KafKaUtil.instanceProducer();
				if(null==this.producer){
					logger.error("生产者初始化失败");
					return 0;
				}
			}
		}else{
			return 0;
		}
		int len=message.length;
		int i;
		for(i=0;i<len;i++){
			 producer.send(new ProducerRecord<String, String>(topic,key, message[i]));
			 logger.debug(this.getClass().getName()+"------ 生产者发送消息"+key+message[i]);
		}
		producer.close();
		return i;
	}
	
	/**
	 * 
	 * @param topic 
	 * @return int  当结果为0时，说明未消费到数据或kafka环境异常
	 */
	public int getMessage(String topic){
		if(null==this.consumer){
			this.consumer=KafKaUtil.instanceConsumer();
			if(null==this.consumer){
					logger.error("消费者初始化失败");
					return 0;
			}
		}
        this.consumer.subscribe(Arrays.asList(topic));
        //boolean needContinue =false;
        //消费者，默认等待10次。当接到数据后，等待数清零
        int i=0;
        int count=0;
        while (i<CONSUMER_LOOP_NUMBER) {
        	i++;
            ConsumerRecords<String, String> records = consumer.poll(100);//0。1s接受一次数据？
            if(records.count()>0){
            	i=0;
            	for (ConsumerRecord<String, String> record : records){
            		
            		logger.debug("offset = %d, key = %s, value = %s",record.offset(), record.key(), record.value());
            		count ++;
                }
            }
            
        }
        if(count==0){
        	logger.debug("消费者未消费到数据");
        }
        consumer.close();
        return count;
    }
}
