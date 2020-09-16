package com.common.mybatis.annotation;


import com.common.mybatis.config.MapperScanConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * @Author Macro
 * @Date 2020/9/10 15:28
 * @Description  mapper注入
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import(MapperScanConfig.class)
public @interface EnableMyBatis {

}
