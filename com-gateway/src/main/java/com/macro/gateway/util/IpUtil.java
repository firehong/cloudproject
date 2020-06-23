package com.macro.gateway.util;


import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * ip地址相关工具类
 * @author macro  2018年1月3日下午3:37:47
 *
 */
public class IpUtil {


  /**
   * ****************************************
   * function: 获取用户真实ip地址
   * @param request
   * @return
   *
   * ========================================
   * created by wuhong on 2017-1-13下午1:20:36
   * description:
   * ========================================
   * modified by name on time
   * description:
   *
   *
   *****************************************
   */
  public static String getIpAddress(ServerHttpRequest request) {
      HttpHeaders headers = request.getHeaders();
      String ip = headers.getFirst("x-forwarded-for");
      if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
          // 多次反向代理后会有多个ip值，第一个ip才是真实ip
          if (ip.indexOf(",") != -1) {
              ip = ip.split(",")[0];
          }
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
          ip = headers.getFirst("Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
          ip = headers.getFirst("WL-Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
          ip = headers.getFirst("HTTP_CLIENT_IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
          ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
          ip = headers.getFirst("X-Real-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
          ip = request.getRemoteAddress().getAddress().getHostAddress();
      }
      return ip;
  }

}