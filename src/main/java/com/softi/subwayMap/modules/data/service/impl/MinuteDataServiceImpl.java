package com.softi.subwayMap.modules.data.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.softi.subwayMap.common.dto.SecondDataDto;
import com.softi.subwayMap.common.util.RdoUtils;
import com.softi.subwayMap.modules.data.entity.MinuteData;
import com.softi.subwayMap.modules.data.dao.MinuteDataMapper;
import com.softi.subwayMap.modules.data.entity.MonthData;
import com.softi.subwayMap.modules.data.entity.SecondData;
import com.softi.subwayMap.modules.data.service.MinuteDataService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.softi.subwayMap.modules.data.service.SecondDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
@Service
@Slf4j
public class MinuteDataServiceImpl extends ServiceImpl<MinuteDataMapper, MinuteData> implements MinuteDataService {

    //汇总后 低一级时间单位的数据进行修改状态的Server
    @Autowired
    SecondDataService lowService;

    //提示信息
    private final  String messageBlock="每分钟汇总秒数据";

    public String getMessageBlock() {
        return messageBlock;
    }

    @Override
    @Transactional
    public int sumSecondDataByUserAndDay(String userId, String date) {
        //先查询当前条件下时候汇总

        //throw new RuntimeException("测试logbak");

        EntityWrapper<MinuteData> entityWrapper=new EntityWrapper<>();
        MinuteData searchData=new MinuteData();
        searchData.setUserId(userId);
        searchData.setMinute(date);
        entityWrapper.setEntity(searchData);
        List<MinuteData> list=this.selectList(entityWrapper);
        //每查找到数据，开始汇总
        if(list.size()==0){
            log.info(messageBlock+"未汇总，开始汇总");
            //汇总
            int i=this.baseMapper.insertSumSecondDataByUserAndDay(userId, date);
            if(i==1){
                //汇总完成后，标记低一级数据为 version=2
                log.info(messageBlock+"完成，将已汇总数据标记版本");
                SecondData lowData = new SecondData();
                lowData.setVersion("2");
                EntityWrapper<SecondData> entityWrapper2=new EntityWrapper<>();
                //查询条件 按照索引顺序来
                entityWrapper2.eq("user_id",userId);
                entityWrapper2.between("data_time",date+":00",date+":59");
                entityWrapper2.eq("version","1");

                EntityWrapper<SecondData> entityWrapper3=new EntityWrapper<>();
                entityWrapper3.eq("user_id",userId);
                entityWrapper3.between("data_time",date+":00",date+":59");
                entityWrapper3.eq("version","1");
                entityWrapper3.last(" for update");
                lowService.selectList(entityWrapper3);
                boolean update = lowService.update(lowData,entityWrapper2);
                if(update){
                    log.info(messageBlock+"成功 修改状态完成");
                    return 1;
                }else{
                    log.error(messageBlock+"成功 修改状态异常，回滚。注意排查问题");
                    throw new RuntimeException(messageBlock+"成功 修改状态异常");
                }
            }else{
                log.error(messageBlock+"出错，回滚。注意排查问题");
                throw new RuntimeException(messageBlock+"汇总出错");
            }
        }else if(list.size()==1){
            log.info(messageBlock+"数据已经汇总过");//，要汇总去实现覆盖汇总方法");
        }else{
            log.info(messageBlock+"汇总数据已重复，用户id："+userId+" 时间："+date+" 去数据库清理 ");
        }
        return 0;
    }

    @Override
    public Page<MinuteData> selectPageByDto(SecondDataDto data) {
        EntityWrapper<MinuteData> entityWrapper=new EntityWrapper<>();
        Page<MinuteData> page=new Page<>();
        //封装分页查询条件，或者使用默认值
        RdoUtils.setPageByDto(page,data);
        //封装条件 按照 索引顺序
        if(data.getUserId()!=null&& !StringUtils.isEmpty(data.getUserId())){
            entityWrapper.eq("user_id",data.getUserId());
        }
        //分
        if (data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$")){
            entityWrapper.eq("minute",data.getDataTime());
        }//小时
        else if(data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}$")){
            entityWrapper.between("minute",data.getDataTime()+":00",data.getDataTime()+":59");
        }
        //天
        else if(data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2}\\s?$")){
            entityWrapper.between("minute",data.getDataTime()+" 00:00",data.getDataTime()+" 23:59");
        }
        else{
            return null;
        }
        return  this.selectPage(page,entityWrapper);
    }

}
