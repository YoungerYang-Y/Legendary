package pers.legendary.common.mbg.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description: Mybatis-Plus 配置 自动填充
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2023-09-06 22:42:32
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //根据名称设置属性值
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //根据名称设置属性值
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
