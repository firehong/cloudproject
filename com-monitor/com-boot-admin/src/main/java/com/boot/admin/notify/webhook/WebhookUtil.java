package com.boot.admin.notify.webhook;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Macro
 * @Date 2020/7/2 8:46
 * @Description  企业微信机器人消息通知
 */
@Slf4j
public class WebhookUtil {

    /**
     * 发送企业微信文本消息通知
     * @Param
     * @Return
     */
    public static void sendText(String url, Map<String, Object> content){
        try {
            Map param = new HashMap<>();
            param.put("msgtype","text");
            param.put("text",content);
            String str = HttpUtil.post(url, JSON.toJSONString(param));
            log.info("[ webhook ] 企业微信机器人通知：{}", str);
        }catch (Exception e){
            log.info("[ webhook ] 企业微信机器人通知异常：{}", e.getMessage(), e);
        }
    }

    /**
     * 发送企业微信markdown消息通知
     * @Param
     * @Return
     */
    public static void sendMarkDown(String url, Map<String, Object> content){
        try {
            Map param = new HashMap<>();
            param.put("msgtype", "markdown");
            param.put("markdown", content);
            String str = HttpUtil.post(url, JSON.toJSONString(param));
            log.info("[ webhook ] 企业微信机器人通知：{}", str);
        }catch (Exception e){
            log.info("[ webhook ] 企业微信机器人通知异常：{}", e.getMessage(), e);
        }
    }



}
