package com.tools.api.feign.fallback;


import com.tools.api.feign.MqFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqFeignClientFallbackFactory implements FallbackFactory<MqFeign> {

    @Override
    public MqFeign create(Throwable throwable) {
        return new MqFeign() {
            @Override
            public boolean sendMsg(String key, String msg) {
                log.error("[ mq ] mq消息发送失败,key:{} msg:{}", key, msg, throwable);
                return false;
            }
        };
    }

}
