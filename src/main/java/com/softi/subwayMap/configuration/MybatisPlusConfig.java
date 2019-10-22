package com.softi.subwayMap.configuration;
 
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
/**
 * @author ya
 * @date 2018/7/19 20:27
 */
@Configuration
@MapperScan("com.softi.subwayMap.modules.*.dao")
public class MybatisPlusConfig {
 
    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
 
 
}
