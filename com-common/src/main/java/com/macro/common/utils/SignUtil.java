package com.macro.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @auther Macro
 * @date 2019/11/8 9:50
 * @Description 签名工具类
 */
@Slf4j
public class SignUtil {

    /**
     * @date 2019/11/8 10:02
     * @Description 签名字符串生成
     * @Param
     */
    public static String sign(Map params, String mchKey) {
        SortedMap<String, String> sortedMap = new TreeMap<>(params);
        StringBuilder toSign = new StringBuilder();
        for (String key : sortedMap.keySet()) {
            String value = params.get(key).toString();
            if (value!=null && !"".equals(value) && !"sign".equals(key)
                    && !"key".equals(key)) {
                toSign.append(key).append("=").append(value).append("&");
            }
        }
        toSign.append("key=").append(mchKey);
        log.debug("sign:{}",toSign.toString());
        return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }

    public static String voteSign(Map params, String mchKey) {
        SortedMap<String, String> sortedMap = new TreeMap<>(params);
        StringBuilder toSign = new StringBuilder();
        for (String key : sortedMap.keySet()) {
            String value = params.get(key).toString();
            toSign.append(key).append("=").append(value).append("&");
        }
        toSign.append(mchKey);
        log.debug("sign:{}",toSign.toString());
        return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }


    public static String getRandomString(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }

}
