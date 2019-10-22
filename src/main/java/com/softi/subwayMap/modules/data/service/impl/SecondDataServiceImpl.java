package com.softi.subwayMap.modules.data.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.softi.subwayMap.common.dto.SecondDataDto;
import com.softi.subwayMap.common.util.RdoUtils;
import com.softi.subwayMap.modules.data.entity.SecondData;
import com.softi.subwayMap.modules.data.dao.SecondDataMapper;
import com.softi.subwayMap.modules.data.service.SecondDataService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
@Service
public class SecondDataServiceImpl extends ServiceImpl<SecondDataMapper, SecondData> implements SecondDataService {

    @Override
    public Page<SecondData> selectPageByDto(SecondDataDto data) {
        EntityWrapper<SecondData> entityWrapper=new EntityWrapper<>();
        Page<SecondData> page=new Page<>();
        //封装分页查询条件，或者使用默认值
        RdoUtils.setPageByDto(page,data);
        //封装条件 按照 索引顺序
        if(data.getUserId()!=null&& !StringUtils.isEmpty(data.getUserId())){
            entityWrapper.eq("user_id",data.getUserId());
        }
        //秒
        if(data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")){
            entityWrapper.eq("data_time",data.getDataTime());
        }//分
        else if (data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$")){
            entityWrapper.between("data_time",data.getDataTime()+":00",data.getDataTime()+":59");
        }//小时
        else if(data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}$")){
            entityWrapper.between("data_time",data.getDataTime()+":00:00",data.getDataTime()+":59:59");
        }
        //天
        else if(data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2}\\s?$")){
            entityWrapper.between("data_time",data.getDataTime()+" 00:00:00",data.getDataTime()+" 23:59:59");
        }
        else{
            return null;
        }
        return  this.selectPage(page,entityWrapper);
    }
}
