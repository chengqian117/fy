package com.softi.subwayMap.modules.data.dao;

import com.softi.subwayMap.modules.data.entity.DayData;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface DayDataMapper extends BaseMapper<DayData> {
    int insertSumHourDataByUserAndDay(String userId,String date);

}
