package com.softi.subwayMap.modules.data.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.softi.subwayMap.common.dto.SecondDataDto;
import com.softi.subwayMap.common.util.RdoUtils;
import com.softi.subwayMap.common.vo.Result;
import com.softi.subwayMap.modules.data.entity.SecondData;
import com.softi.subwayMap.modules.data.service.SecondDataService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
@RestController
@RequestMapping("/secondData")
@Api(value = "秒数据",tags = "秒数据" )
@Slf4j
public class SecondDataController {

    @Autowired
    SecondDataService secondDataService;
    @ApiOperation(value = "通过条件分页查询秒数据")
    @GetMapping
    public Result getList( @ModelAttribute SecondDataDto data){
        Result result = new Result();
        if(data!=null){
            if(data.getDataTime()==null||!data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2}.*")){
                RdoUtils.setResultError(result,RdoUtils.UN_FORMAT_DATE);
            }else{
                Page<SecondData> secondDataPage = secondDataService.selectPageByDto(data);
                if(secondDataPage==null){
                    log.error("秒查询结果为null 数据格式不正确");
                    RdoUtils.setResultError(result,RdoUtils.UN_FORMAT_DATE);
                }else{
                   RdoUtils.setResultByData(result,secondDataPage);
                }
            }
        }else {
            RdoUtils.setResultError(result,RdoUtils.NULL_OBJECT);
        }
        return result;
    }



}

