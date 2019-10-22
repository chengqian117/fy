package com.softi.subwayMap;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.softi.subwayMap.modules.data.entity.SecondData;
import com.softi.subwayMap.modules.data.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Test3 {

    //@Autowired
    //DayDateService dayDateService;
    @Test
    @Transactional
    public void test(){
//        Page<DayDate> page=new Page<DayDate>();
//        page.setSize(10);
//        page= dayDateService.selectPage(page);
//        Assert.assertTrue(page.getRecords().size()==10);
//        page.getRecords().stream().forEach(System.out::println);
//
//        EntityWrapper<DayDate> entityWrapper=new EntityWrapper<>();
//        entityWrapper.eq("user_id","1");
//        entityWrapper.between("data_time","2019-10-12 17:00:00","2019-10-12 17:03:00");
//        entityWrapper.eq("version","1");
//
//        List<DayDate> dayDates = dayDateService.selectList(entityWrapper);
//        System.out.println(dayDates.size());


        try {
            Thread.sleep(5000L);

//            EntityWrapper<DayDate> entityWrapper2=new EntityWrapper<>();
//            entityWrapper2.eq("user_id","1");
//            entityWrapper2.between("data_time","2019-10-12 17:00:00","2019-10-12 17:03:01");
//            entityWrapper2.eq("version","1");
//
//            List<DayDate> dayDates2 = dayDateService.selectList(entityWrapper2);
//            System.out.println(dayDates2.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        while(true) {
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            DayDate da = new DayDate();
//            da.setUserId("3");
//            da.setUserData(new BigDecimal(Math.random() * (100) + 0).setScale(2, BigDecimal.ROUND_DOWN));
//            da.setDataTime(new Date(Math.round(Math.random() * (1577721600000L - 1546272000000L) + 1546272000000L)));
//            boolean t = dayDateService.insert(da);
//            if (t) {
//                System.out.println("insert :data" + da.toString());
//            }
//            try {
//                Thread.sleep(20L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

    @Autowired
    MonthDataService monthDataService;
    @Autowired
    MinuteDataService minuteDataService;
    @Autowired
    HourDataService hourDataService;
    @Autowired
    DayDataService dayDataService;
    @Autowired
    YearDataService yearDataService;
    @Autowired
    SecondDataService secondDataService;
    @Test
    @Transactional
    public  void test2(){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date date=new Date();
//        long l=date.getTime()-60*1000;
//        String s=simpleDateFormat.format(new Date(l));
  //      monthDataService.sumDayDataByUserAndDay("2","2019-10-14 11:25");
        //minuteDataService.sumSecondDataByUserAndDay("2","2019-10-17 14:18");
        //hourDataService.sumMinuteDataByUserAndDay("2","2019-10-17 14");
        //dayDataService.sumHourDataByUserAndDay("2","2019-10-17");
        //monthDataService.sumDayDataByUserAndDay("2","2019-10");
        //yearDataService.sumMonthDataByUserAndDay("2","2019");
        String date="2019-10-18 10:54";
        EntityWrapper<SecondData> entityWrapper3=new EntityWrapper<>();
        entityWrapper3.eq("user_id","1");
        entityWrapper3.between("data_time",date+":00",date+":59");
        entityWrapper3.eq("version","1");
        //entityWrapper3.last(" for update");
        //secondDataService.selectList(entityWrapper3);
        //throw new RuntimeException("a");
    }


}
