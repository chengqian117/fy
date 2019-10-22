package com.softi.subwayMap.modules.data.dao;

import com.softi.subwayMap.modules.data.entity.MonthData;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface MonthDataMapper extends BaseMapper<MonthData> {
    int insertSumDayDataByUserAndDay(String userId,String date);

}
