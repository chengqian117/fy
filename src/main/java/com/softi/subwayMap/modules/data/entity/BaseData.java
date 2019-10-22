package com.softi.subwayMap.modules.data.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BaseData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;
    /**
     * 版本 1 是新数据 2 是统计过的数据
     */
    private String version;
    /**
     * 数据总和
     */
    @TableField("sum_data")
    private BigDecimal sumData;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据条数
     */
    @TableField("count_data")
    private BigDecimal countData;
    /**
     * 数据平均值
     */
    @TableField("avg_data")
    private BigDecimal avgData;




}
