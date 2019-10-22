package com.softi.subwayMap.modules.data.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.softi.subwayMap.common.dto.SecondDataDto;
import com.softi.subwayMap.modules.data.entity.YearData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface YearDataService extends IService<YearData> {
    /**
     * @Description: 根据用户和年汇总月数据
     * @Param: userId 用户id
     * @Param: date 当前年 2019
     * @return: int 1 表示汇总完成 0表示汇总中存在问题
     * @Author: cq
     * @Date: 2019/10/17
     */
    int sumMonthDataByUserAndDay(String userId, String date);

    Page<YearData> selectPageByDto(SecondDataDto data);

}
