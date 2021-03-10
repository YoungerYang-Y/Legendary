package com.yy.mbg.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Administrator
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.yy.mbg.domain.mapper")
public class MybatisPlusConfig {

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则
     * 旧版：PaginationInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * Sql执行时间记录拦截器
     * */
    @Bean
    public SqlCostInterceptor customInsertInterceptor() {
        return new SqlCostInterceptor();
    }

    /**
     * 乐观锁配置
     */
    @Bean
    public OptimisticLockerInterceptor OptimisticLockerInnerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
