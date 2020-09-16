package com.common.cache.annotation;

import com.common.cache.RedisLockServerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author Macro
 * @Date 2020/9/10 15:28
 * @Description  redis分布式锁服务
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import(RedisLockServerAutoConfiguration.class)
public @interface EnableRedisLockServer {
}
