package com.softi.subwayMap.common.common;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;


public interface BaseCrudService<T>  extends IService<T> {

    //新增
    void addOne(T t);
    //根据主键批量删除
    void deleteByIds(String objIds);
    //通过主键修改
    void updateByEntity(T t);
    //分页加条件查询
    Page<T> findPageByEntity(T t, QueryRequest request);


}
