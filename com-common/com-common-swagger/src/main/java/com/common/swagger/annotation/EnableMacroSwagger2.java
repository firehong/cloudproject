package com.common.swagger.annotation;


import com.common.swagger.config.Swagger2AutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author Macro
 * @Date 2020/9/10 15:28
 * @Description  swagger2
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import({ Swagger2AutoConfiguration.class })
public @interface EnableMacroSwagger2 {
}
