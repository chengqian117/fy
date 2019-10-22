package com.softi.subwayMap.modules.data.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.softi.subwayMap.common.dto.SecondDataDto;
import com.softi.subwayMap.modules.data.entity.SecondData;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface SecondDataService extends IService<SecondData> {


    Page<SecondData> selectPageByDto(SecondDataDto data);

}
