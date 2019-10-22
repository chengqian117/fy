package com.softi.subwayMap.modules.data.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.softi.subwayMap.common.dto.SecondDataDto;
import com.softi.subwayMap.modules.data.entity.MinuteData;
import com.baomidou.mybatisplus.service.IService;
import com.softi.subwayMap.modules.data.entity.SecondData;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface MinuteDataService extends IService<MinuteData> {

    /**
     * @param userId 用户id
     * @param minute 当前分钟 2019-10-17 11:12
     * @Description:根据用户和分钟汇总秒数据
     * @return: int 1 表示汇总完成 0表示汇总中存在问题
     * @Author: cq
     * @Date: 2019/10/17
     */
    int sumSecondDataByUserAndDay(String userId, String minute);


    Page<MinuteData> selectPageByDto(SecondDataDto data);

}
