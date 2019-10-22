package com.softi.subwayMap.modules.data.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
@TableName("user_day_data")
@Data
public class DayData  extends BaseData {


    /**
     * 天
     */
    private String day;

}
