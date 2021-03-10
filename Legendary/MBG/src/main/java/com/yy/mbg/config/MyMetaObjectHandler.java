package com.yy.mbg.config;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyMetaObjectHandler: 自定义自动填充策略
 *
 * @Author: YangYang
 * @Date: 2021/2/28 14:35
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        // 起始版本 3.3.0(推荐使用)
        this.setFieldValByName("gmtCreate", DateTime.now(), metaObject);
        this.setFieldValByName("status", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        // 起始版本 3.3.0(推荐)
        this.setFieldValByName("gmtModified", DateTime.now(), metaObject);
    }
}
