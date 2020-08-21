package com.tools.mq.controller;


import com.tools.mq.service.MqTopicProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Macro
 * @Date 2020/8/21 15:06
 * @Description  mq 消息总线
 */
@RestController
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private MqTopicProducerService mqTopicProducerService;

    /**
     * 发送队列消息
     * @Param key 主题消息 路由key
     * @Param msg json消息
     * @Return true false
     */
    @GetMapping()
    public boolean sendMsg(@RequestParam String key, @RequestParam String msg) {
        return mqTopicProducerService.sendMqMsg(key, msg);
    }


}
