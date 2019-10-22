package com.softi.subwayMap;

import com.baomidou.mybatisplus.plugins.Page;
import com.softi.subwayMap.common.redis.utils.RedisUtil;
import com.softi.subwayMap.common.util.RedisConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

/*
public class Test2 {
	
}

*/

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Test2 {
	
	/*@Autowired
	LineConfigService lineConfigService;
	@Autowired
	TrackSegmentService service;*/
	//@Autowired
	//SpeedLimitService speedLimitService;
//	@Autowired
//	VideoFrontEquipmentService VideoEquService;
	@Test
	public void test(){
		//List<LineConfig>  ada=lineConfigService.selectByProjectId("a9483a398edc4dd8873d64e6ff85ba49");
		//stationServiceImpl.selectStationsByProjectId("a9483a398edc4dd8873d64e6ff85ba49");
		//TrainWarningMapper.selectTrainWaring();
		//service.selectTrackSegmentByProjectId("a9483a398edc4dd8873d64e6ff85ba49");
		//speedLimitService.selectSpeedLimitsByProjectId("a9483a398edc4dd8873d64e6ff85ba49");
		
		
//		EntityWrapper<VideoFrontEquipment> wrapper=new EntityWrapper<VideoFrontEquipment>();
//		wrapper.setEntity(new VideoFrontEquipment());
		//wrapper.eq("EQUIPMENT_ID", "4a894bd1848a464cab038f68eea2b77a");
		
		
//		wrapper.setSqlSelect(new String[]{
//				"VIDEO_FRONT_EQUIPMENT_SEQ_NO",
//				"FACE_RECG_FUNC_UNLOCK_FLAG",
//				"HELMET_FUNC_UNLOCK_FLAG",
//				"GUARD_CLOTHE_FUNC_UNLOCK_FLAG",
//				"BORDER_CHECK_FUNC_UNLOCK_FLAG"
//
//				});
		Page<Object> page=new Page<Object>();
//		page.setSize(10);
//		page.setCurrent(1);
//		page=VideoEquService.selectPage(page, wrapper);
//		List<VideoFrontEquipment> records=page.getRecords();
//		System.out.println(page.getTotal());
//		//VideoEquService.selectList(wrapper);
//		for(VideoFrontEquipment aa:records){
//			System.out.println(aa.toString());
//		}
		
	}
	
	
//	@Autowired
//	TrainWarningMapper mapper;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	//@Test
	//@Transactional
	public void insertWaring(){
		//1.获取事务定义
		//DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		//2.设置事务隔离级别，开启新事务
		//def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		//3.获得事务状态
		//TransactionStatus status = transactionManager.getTransaction(def);
		
		
		
		while(true){
			//List<TrainWarning> records=new ArrayList<TrainWarning>();
			JedisPool pool=RedisUtil.instanseLone();
			Jedis jedis=pool.getResource();
			Set<String> ids=jedis.keys(RedisConstants.REDIS_WARNING_TRAIN_KEY+"*");
			for(String id:ids){
				
				Map<String,String> map=jedis.hgetAll(id);
				if(null==map||map.size()==0){
					continue;
				}
//				TrainWarning t=(TrainWarning) BeanUtilss.getBeanByMap(map, TrainWarning.class);
//				t.setLocmtvWarningId(IdGen.uuid());
//				t.setLocmtvWarningCd(IdGen.uuid().substring(8, 26));
//				records.add(t);
			
			}
		
//			mapper.deleteAll();
//			mapper.insertTrainWarnings(records);
			jedis.close();
			pool.close();
			//transactionManager.commit(status);
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//@Test
	/*
	public void test(){
		String lineId;
		lineId="84ed62acf18b424fb9ebc29c74990181";
		Qingjia qingjia=new Qingjia();
		qingjia.setQingjiaId(IdGen.uuid());
		qingjia.setQingjiaTime("我 请假一天");
		qingjia.setQingjiaName("我");
		qingjiaservice.startQingjia(qingjia);
		//MapJson map=constructService.getConstructDataByLineId(lineId);
		//JSONObject json=JSONObject.fromObject(map);
		try {
	        //    输出Ascii到单文件
	        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
	                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
	                .withOutputLanguage(Language.ZH)
	                .withPathsGroupedBy(GroupBy.TAGS)
	                .withGeneratedExamples()
	                .withoutInlineSchema()
	                .build();
	 
	        
				Swagger2MarkupConverter.from(new URL("http://localhost:8001/subwayMap/v2/api-docs"))
				        .withConfig(config)
				        .build()
				        .toFile(Paths.get("./docs/asciidoc/generated/all"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//System.out.println(json);
	}
*/
	/*
	public static void main(String[] args) {
		
		Train train=new Train();
		train.setLocmtvId("asd");
		train.setEquipmentId("sad");
		ConvertUtils.register(new DateConverter(null), Date.class); 
		ConvertUtils.register(new BigDecimalConverter(null),BigDecimal.class);
		Map<String,String> map=new BeanMap(train);
		System.out.println(map);
		Train train2=new Train();
		try {
			
			BeanUtils.populate(train2, map);
			System.out.println(train2.getEquipmentId());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConvertUtils.register(new BigDecimalConverter(null),BigDecimal.class);
		Locposit ll=new Locposit();
		ll.setSpeed(new BigDecimal(888));
		Map<String, String> map2;
		try {
			map2 = BeanUtils.describe(ll);
			Set<String> set=map2.keySet();
			for(String s:set){
				System.out.println(s);
			}
			System.out.println(map2.get("speed").toString());
			System.out.println(map2.get("equipmentId"));
			removeMapEmptyValue(map2);
			JedisPool pool=RedisUtil.instanseLone();
			Jedis jedis=pool.getResource();
			jedis.hmset("TRAINtest", map2);
			jedis.close();
			pool.close();
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		String message=JSONObject.toJSONString(train);
		System.out.println(message);
		
		Train record2=JSON.parseObject(message, Train.class);*/
    	//Train record=JSONObject.toJavaObject(json,Train.class);
    	//System.out.println(record2);
		//String [] sss={"你","是","不","是","sha"};
		//ProducerService ss=new ProducerService("test", Constants.KAFKA_TOPIC, sss);
		//ss.start();
		//ConsumerService ss=new ConsumerService(Constants.KAFKA_TOPIC);
		//ss.start();
		//KafkaService ss=new KafkaService();
		//ss.senMessage(Constants.KAFKA_TOPIC, "ad", sss);
		/*
		String x="10000000000000";
		long l1=Long.parseLong(x);
		Date date=new Date(l1);
		//date.setTime(10000000000000L);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
		System.out.println(sdf.format(date));
		long l=date.getTime();
		System.out.println(l);
		*/
	//}
	
	
	
	
}
/* 
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Test2 {
	
	@Autowired
	ViewTableMapper viewTableMapper;
	
	@Test
	public void test(){
		ViewTable t=viewTableMapper.selectByName("investor");
		JSONObject json = JSONObject.fromObject(t);
		VueInstance v=FormatVueInstance.format(t);
		JSONObject json2 = JSONObject.fromObject(v);
		System.out.println("---------------");
		System.out.println("---------------");
		System.out.println(json);
		System.out.println(json2);
		System.out.println("---------------");
		System.out.println("---------------");
	}
}
*/
