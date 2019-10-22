package com.softi.subwayMap.common.kafka.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.softi.subwayMap.common.kafka.utils.KafKaUtil;
import com.softi.subwayMap.common.util.Constants;


public class ProducerService extends Thread {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String key = null;

	private String sendTopic = null;

	private KafkaProducer<String, String> producer;

	private String[] message;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSendTopic() {
		return sendTopic;
	}

	public void setSendTopic(String sendTopic) {
		this.sendTopic = sendTopic;
	}

	public KafkaProducer<String, String> getProducer() {
		return producer;
	}

	public void setProducer(KafkaProducer<String, String> producer) {
		this.producer = producer;
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}
	/**
	 * 
	 * @param key
	 * @param sendTopic
	 * @param message
	 */
	public ProducerService(String key, String sendTopic,String... message) {
		super();
		this.key = StringUtils.isEmpty(key) ? "key" : key;
		this.sendTopic = StringUtils.isEmpty(sendTopic) ? Constants.KAFKA_TOPIC_TRAIN
				: sendTopic;
		// this.producer = producer;
		
		this.message = message;
	}

	@Override
	public void run() {
		super.run();
		
		if (null == this.producer) {
			this.producer = KafKaUtil.instanceProducer();
			if (null == this.producer) {
				logger.error("生产者初始化失败");
			}
		}
		int len = message.length;
		if (len == 0) {
			logger.error("由于生产者线程初始化未输入有效数据  message 为空");
		}
		int i;
		for (i = 0; i < len; i++) {
			producer.send(new ProducerRecord<String, String>(sendTopic, key,
					message[i]));
			logger.debug(this.getClass().getName() + "------ 生产者发送消息" + key
					+ message[i]);
		}
		producer.close();
	}
	
}
