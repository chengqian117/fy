package com.softi.subwayMap.modules.data.dao;

import com.softi.subwayMap.modules.data.entity.HourData;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface HourDataMapper extends BaseMapper<HourData> {

    int insertSumMinuteDataByUserAndDay(String userId,String date);
}
