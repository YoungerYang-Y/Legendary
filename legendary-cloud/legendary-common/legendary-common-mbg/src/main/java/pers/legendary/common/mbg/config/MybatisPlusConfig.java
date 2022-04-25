package pers.legendary.common.mbg.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Description: Mybatis-Plus 配置
 *  TODO
 *      1. 开启二级缓存
 *      2. 将XML移动到resource文件夹中
 *      3. 将这个Config文件移动到别的地方
 *
 * @author YangYang
 * @date 2022-03-19 20:07:58
 * @version 1.0.0
 */
@Configuration
@MapperScan(basePackages = "pers.legendary.common.mbg.rbac.mapper")
public class MybatisPlusConfig{

    /**
    * 新的分页插件
    * 旧版：PaginationInterceptor
    */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
