package com.common.cache.annotation;

import com.common.cache.RedisServerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author Macro
 * @Date 2020/9/10 15:28
 * @Description  redis服务
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import(RedisServerAutoConfiguration.class)
public @interface EnableRedisServer {

}
