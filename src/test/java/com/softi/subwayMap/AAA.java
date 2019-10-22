package com.softi.subwayMap;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class AAA {
/**
	@Test
	public void testmessage(){
		
		//String[] projectid=new String[]{"23100c11a1e24c49a9f5da3b225cb41b",		"1e07e3f311db4234bc69b362bf9263a3",		"a55710783d904f0fb5e9d79fe1608ad0",		"23100c11a1e24c49a9f5da3b225cb41b",		"a55710783d904f0fb5e9d79fe1608ad0",		"23100c11a1e24c49a9f5da3b225cb41b"};
		String[] Id=new String[]{"448198a9e35b450f9da7cd1801f47761",		"5151324bb7944be4810fab514c570a80",		"6dc54d37063f4e568c8d67dfe51a4a51",		"9d435a0ed4194477812d92298a7f1291",		"ad3bb39e43ba4437b5ca9fa5fd0bd02d",		"f9dcde66da434bf5996561e6f80e1248"};
		String[] startTackId=new String[]{"32461427ed6340c9848fe8884d930c32",		"32461427ed6340c9848fe8884d930c32",		"c64c00ced6c74228a24a4fc24dc99e4f",		"c64c00ced6c74228a24a4fc24dc99e4f",		"32461427ed6340c9848fe8884d930c32",		"32461427ed6340c9848fe8884d930c32"};
		long[] startmil=new long[]{
				1215100,		566600,		526900,		1208700,		526900,		799550,
		};
		long[] endmil=new long[]{
				1217320,
				580000,
				515629,
				515629,
				515629,
				515629

		};
		
		long [] limit=new long[]{
				60,
				50,
				60,
				60,
				60,
				30,

		};
		JedisPool pool=RedisUtil.instanseLone();
		Jedis jedis=pool.getResource();
		for(int i=0;i<6;i++){
			SpeedLimit aa=new SpeedLimit();
			//aa.setProjectId(projectid[i]);
			aa.setSpeedLimitZoneId(Id[i]);
			aa.setStartTrackSegmId(startTackId[i]);
			aa.setStartMil(BigDecimal.valueOf(startmil[i]));
			aa.setEndMil(BigDecimal.valueOf(endmil[i]));
			aa.setMaxSpeedLimit(BigDecimal.valueOf(limit[i]));
			aa.setLineId("84ed62acf18b424fb9ebc29c74990181");
			String key="LIMIT_ZONE"+Id[i];
			Map<String,String> map=BeanUtilss.getMapByBean(aa);
			jedis.hmset(key, map);
		}
		String [] a2=new String[]{
				"448198a9e35b450f9da7cd1801f47761",
				"9d435a0ed4194477812d92298a7f1291",
				"f9dcde66da434bf5996561e6f80e1248"

		};
		jedis.lpush(RedisConstants.REDIS_GET_LIMIT_ZONE_KEY+"84ed62acf18b424fb9ebc29c74990181", Id);
		//jedis.lpush("LIMIT_ZONE:PROJECT:"+"23100c11a1e24c49a9f5da3b225cb41b", a2);
		//jedis.lpush("LIMIT_ZONE:PROJECT:"+"1e07e3f311db4234bc69b362bf9263a3", "5151324bb7944be4810fab514c570a80");

		JedisPool pool=RedisUtil.instanseLone();
		Jedis jedis=pool.getResource();
		for(int i=0;i<5;i++){
			ProtectArea aa=new ProtectArea();
			String id=IdGen.uuid();
			aa.setProjectId("23100c11a1e24c49a9f5da3b225cb42b");
			aa.setProtectAreaId(id);
			aa.setProtectAreaName("测试"+i);
			aa.setStartTrackSegmId("f9dcde66da434bf5996561e6f80e1248");
			aa.setStartMil(BigDecimal.valueOf(300+10000*i));
			aa.setEndMil(BigDecimal.valueOf(300+10000*(i+1)));
			Map<String,String> map=BeanUtilss.getMapByBean(aa);
			jedis.hmset(RedisConstants.REDIS_PROTECTAREA_KEY+id, map);
			jedis.lpush(RedisConstants.REDIS_GET_PROTECTAREA_KEY+"23100c11a1e24c49a9f5da3b225cb42b", id);
		}
		jedis.close();
		pool.close();
	}
	@Test
	//随机修改车辆的位置和速度
	public void train(){
		
		//JedisPool pool=RedisUtil.instanseLone();
		//Jedis jedis=pool.getResource();
		
		
		//Set <String> ss=jedis.smembers(RedisConstants.REDIS_GET_TRAIN_KEY+"84ed62acf18b424fb9ebc29c74990181");
		
		String[] sss=new String[]{"6e1bd4187ae348289dedef7857547e17",
		                		"3b9543bba6074bd98963ed66ee6b66bb",
		                		"25af54980e174307a51f9a5e113f56f6",
		                		"9d26abae46a34cb1b4447b73521db158"
		};
		String[] sss2=new String[]{"7d4c86ea0d1a40b79c403effb0fa90a1",
					"a2f56da8ad984c29adb98da471f0556b",
					"e720c81678d648d081191c0a17e6754e",
					"e65a42232f9541a1b83ecb707da04a71"
};
		int i2=0;
		for(String s:ss){
			sss[i2]=s;
			i2++;
		}
	
		while(true){
			int number=(int) (Math.random()*sss.length);
			int number2=(int) (Math.random()*sss2.length);
			//Train train=(Train) BeanUtilss.getBeanByMap(jedis.hgetAll(sss[number]), Train.class);
			SlaveLocation loc=new SlaveLocation();
			loc.setUpdateTime(new Date());
			loc.setLocMil(BigDecimal.valueOf(Math.random()*(51359-384)+384).setScale(1,BigDecimal.ROUND_DOWN));
			loc.setTrackSegmId("32461427ed6340c9848fe8884d930c32");
			loc.setLocEquipmentId(sss2[number2]);

			Train train=new Train();
			train.setUpdateTime(new Date());
			train.setLocMil(BigDecimal.valueOf(Math.random()*(51359-384)+384).setScale(1,BigDecimal.ROUND_DOWN));
			train.setSpeed(BigDecimal.valueOf(Math.random()*30+50).setScale(1,BigDecimal.ROUND_DOWN));
			train.setTrackSegmId("32461427ed6340c9848fe8884d930c32");//现在是线段id 之后可能要改成轨道线
			train.setLocmtvId(sss[number]);
			try {
				String message=JSONObject.toJSONString(train, SerializerFeature.WriteDateUseDateFormat);
				String message2=JSONObject.toJSONString(loc, SerializerFeature.WriteDateUseDateFormat);
				System.out.println("修改列车速度信息时，预存kafka"+message);
				ProducerService  producerService=new ProducerService(Constants.KAFKA_KEY_TRAIN_UPDATE, Constants.KAFKA_TOPIC_TRAIN, message);
				producerService.start();
				ProducerService  producerService2=new ProducerService(Constants.KAFKA_KEY_SLAVE_UPDATE, Constants.KAFKA_TOPIC_SLAVE, message2);
				producerService2.start();
				Thread.sleep((long) (Math.random()*1000));
			} catch (NumberFormatException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		String ss[]=new String[30];
		for(int i=10;i<40;i++){
			Train train=new Train();
			//train.setProjectId("23100c11a1e24c49a9f5da3b225cb42b");
			String id= IdGen.uuid();
			train.setLocmtvId(id);
			train.setUpdateTime(new Date());
			train.setLocMil(BigDecimal.valueOf(Math.random()*570000));
			train.setSpeed(BigDecimal.valueOf(Math.random()*40+50));
			train.setTrackSegmId("32461427ed6340c9848fe8884d930c32");
			train.setOnlineStatCd("1");
			train.setEquipmentName("测试列车"+i);
			train.setLocmtvCd("TRA"+i);
			Map <String,String> map=BeanUtilss.getMapByBean(train);
			jedis.hmset("TRAIN"+id, map);
			ss[i-10]=id;
		}
		jedis.sadd(RedisConstants.REDIS_GET_TRAIN_KEY+"84ed62acf18b424fb9ebc29c74990181", ss);

		
	
	}
	@Test
	public void test2(){
		List<Train> trains=new ArrayList<Train>();
		JedisPool pool=RedisUtil.instanseLone();
		Jedis jedis=pool.getResource();
		String key=RedisConstants.REDIS_GET_TRAIN_KEY+"23100c11a1e24c49a9f5da3b225cb42b";
		Set<String> trainIds=jedis.smembers(key);
		Set<String> trainIds2=jedis.keys("TRAIN*");
		Set<String> trainIds4=new HashSet<String> ();
		for(String s:trainIds2){
			if(s.matches("TRAIN:PROJECT:.*")){
				
			}else{
				s=s.substring(5);
				trainIds4.add(s);
			}
		}
		Set<String> trainIds3=new HashSet<String> ();
		
		trainIds3.addAll(trainIds);
		trainIds.removeAll(trainIds4);
		trainIds4.removeAll(trainIds3);
		
		System.out.println("111111111111");
		for(String s:trainIds){
			System.out.println(s);
		}
		
		System.out.println("22222222");
		for(String s:trainIds4){
			System.out.println(s);
		}
		//return trains;
	}

	**/

	@Test
	public void test2(){
//		Stream<Integer> stream4 = Stream.iterate(0,(x) -> x+2);
//		stream4.forEach(System.out::println);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date=new Date();
		long l=date.getTime()-60*1000;
		String s=simpleDateFormat.format(new Date(l));
		System.out.println(s);
	}
	@Test
	public  void  test3(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date=new Date();
		//当前时间倒退一分钟获取上一分的样式
		long l=date.getTime()-24*60*60*1000;
		String date2=simpleDateFormat.format(new Date(l));
		System.out.println(date2);
	}
}
