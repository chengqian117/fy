package com.softi.subwayMap.common.kafka.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softi.subwayMap.common.kafka.utils.KafKaUtil;

public class ConsumerService extends Thread{
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	 // 并发的线程数
    private static int CONCURRENCY_THREAD_NUM = 2;

    private ConsumerConnector consumer;

    // 待消费的topic
    private String topic;

	public static int getCONCURRENCY_THREAD_NUM() {
		return CONCURRENCY_THREAD_NUM;
	}

	public static void setCONCURRENCY_THREAD_NUM(int cONCURRENCY_THREAD_NUM) {
		CONCURRENCY_THREAD_NUM = cONCURRENCY_THREAD_NUM;
	}

	public ConsumerConnector getConsumer() {
		return consumer;
	}

	public void setConsumer(ConsumerConnector consumer) {
		this.consumer = consumer;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * 
	 * @param consumer
	 * @param topic
	 */
	public ConsumerService( String topic) {
		super();
		//this.consumer = consumer;
		this.topic = topic;
	}

	
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	super.run();
    	if (null == consumer) {
			this.consumer = KafKaUtil.instanceConsumerNew();
			if (null == this.consumer) {
				logger.error("生产者初始化失败");
				return;
			}
		}
    	 Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

         // 后边的Integer表示启动多少个线程来消费指定的Topic
         // 注意： 当该Integer大于待消费Topic的Partition时，多出的线程将无法消费到数据
         topicCountMap.put(topic, new Integer(CONCURRENCY_THREAD_NUM));
         Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
         List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

         logger.info("Consumerstreams size is : " + streams.size());

         // 指定的线程号，仅用于区分不同的线程
         int threadNum = 0;
         for (KafkaStream<byte[], byte[]> stream : streams)
         {
             StreamThread streamThread = new StreamThread(stream, threadNum, topic);
             streamThread.start();
             threadNum++;
         }
    }
    
    private class StreamThread extends Thread
    {
        private KafkaStream<byte[], byte[]> stream = null;

        private int threadNum = 0;

        private String topic = null;

        /**
         * 消费者线程类构造方法
         * @param stream 流
         * @param threadNum 线程号
         * @param topic topic
         */
        public StreamThread(KafkaStream<byte[], byte[]> stream, int threadNum, String topic)
        {
            this.stream = stream;
            this.threadNum = threadNum;
            this.topic = topic;
        }

        public void run()
        {
        	logger.info("Stream Thread " + this.threadNum + " Start.");

            ConsumerIterator<byte[], byte[]> it = stream.iterator();

            logger.info("Stream Thread " + this.threadNum + " receiveing...");

            // 阻塞方法，即当该线程消费的Partition上无最新数据的情况下，则一直等待，直到手动停止
            while (it.hasNext())
            {
            	MessageAndMetadata<byte[], byte[]> message=it.next();
            	logger.info("Stream Thread " + this.threadNum + " receive " + new String(message.message()) + " from "
                        + message.topic());
            }
        }
    }
    
   
}
