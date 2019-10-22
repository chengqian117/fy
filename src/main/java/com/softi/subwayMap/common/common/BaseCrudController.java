package com.softi.subwayMap.common.common;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseCrudController<M extends BaseCrudService<T>,T>  {

    //可配置的日志信息块
    private final String  messageBlock="方法";

    public String getMessageBlock() {
        return messageBlock;
    }

    @Autowired
    private M baseCrudService;
    private String message;


    /**
     * 新增
     */
    @PostMapping
    public void addOne(@RequestBody T t) throws RuntimeException{

        try {
            this.baseCrudService.addOne(t);
        } catch (Exception e) {
            message = "新增"+(StringUtils.isEmpty(getMessageBlock())?t.getClass().toString():getMessageBlock())+"失败";
            log.error(message,e);
            throw new RuntimeException(message);
        }

    }


    /**
     * 删除
     */
    @DeleteMapping("/{objIds}")
    public void deleteByIds(@PathVariable(value = "objIds") String objIds){
        try {
            this.baseCrudService.deleteByIds(objIds);
        } catch (Exception e) {
            message = "删除"+(StringUtils.isEmpty(getMessageBlock())?this.getClass().toString():getMessageBlock())+"失败";
            log.error(message,e);
            throw new RuntimeException(message);
        }

    }

    /**
     * 修改
     */
    @PutMapping
    public void updateByEntity( T t){
        try {
            this.baseCrudService.updateByEntity(t);
        } catch (Exception e) {

            message = "更新"+(StringUtils.isEmpty(getMessageBlock())?t.getClass().toString():getMessageBlock())+"失败";
            log.error(message,e);

            throw new RuntimeException(message);
        }

    }

    /**
     * 分页查
     */
    @GetMapping//(produces = "application/json;charset=UTF-8")
    //@ApiOperation(value="查询"+messageBlock, notes="GET",httpMethod = "GET",produces = "application/json;charset=UTF-8")
    public Map<String, Object> findPageByEntity(QueryRequest request, T t){
        return null;
        //return getDataTable(this.baseCrudService.findPageByEntity(t,request));
    }


    protected Map<String, Object> getDataTable(Page<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", pageInfo.getRecords());
        rspData.put("total", pageInfo.getTotal());
        return rspData;
    }
}
