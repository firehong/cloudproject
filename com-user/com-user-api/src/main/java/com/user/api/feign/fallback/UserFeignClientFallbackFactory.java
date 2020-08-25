package com.user.api.feign.fallback;


import com.common.generator.entity.entity.TbUser;
import com.user.api.feign.UserFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeign> {

    @Override
    public UserFeign create(Throwable throwable) {
        return name -> {
            log.error("通过登录账号查询用户异常:{}", name, throwable);
            return new TbUser();
        };
    }

}
