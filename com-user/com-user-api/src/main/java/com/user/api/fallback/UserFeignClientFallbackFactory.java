package com.user.api.fallback;

import com.macro.common.entity.entity.TbUser;
import com.user.api.UserFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeign> {

    @Override
    public UserFeign create(Throwable throwable) {
        return new UserFeign() {

            @Override
            public TbUser queryUserByName(String name) {
                log.error("通过用户名查询用户异常:{}", name, throwable);
                return new TbUser();
            }


        };
    }

}
