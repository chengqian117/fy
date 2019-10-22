package com.softi.subwayMap.common.scheduled;

import com.softi.subwayMap.common.redis.utils.RedisUtil;
import com.softi.subwayMap.common.util.RedisConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class CheckTrainScheduled {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	private final SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
//	@Autowired
//	TrainService trainService;
	//每天早5点到晚上24点 每三分红一次
	//@Scheduled(cron="0 0/3 5-23 * * * ")
	public void checkTrain(){
		logger.debug("检查列车是否下线任务启动");
		JedisPool pool=RedisUtil.instanseLone();
		Jedis jedis=pool.getResource();
		//查找到所有火车
		Set<String> set=jedis.keys(RedisConstants.REDIS_TRAIN_KEY+"[0123456789abcdef]*");
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			String key=it.next();
			try{
				Map<String,String> map=jedis.hgetAll(key);
				if(!StringUtils.isEmpty(map.get("updateTime"))){
					String locmtvId=map.get("locmtvId");
					//logger.info("");
					String updateTime=map.get("updateTime");
					Calendar c=Calendar.getInstance();
					Date date=c.getTime();
					long now =c.getTimeInMillis();
					Date oldDate=sdf.parse(updateTime);
					c.setTime(oldDate);
					long old=c.getTimeInMillis();
					//如果当前时间大于上次修改时间 20 分钟，则修改当前列车的状态为离线
					if(now-old>20*60*1000){
//						Locposit record =new Locposit();
//
//						record.setLocmtvId(locmtvId);
//						record.setOnlineStatCd("0");
//						trainService.updateTrainOnline(record);
						logger.info("列车编号为:"+locmtvId+"的列车超过20分钟没有活动，修改状态位离线");
						logger.info("当前时间"+sdf2.format(date)+"------最后活跃时间"+sdf2.format(oldDate));
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
}
