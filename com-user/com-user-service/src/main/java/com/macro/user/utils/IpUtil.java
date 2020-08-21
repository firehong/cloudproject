package com.macro.user.utils;

import com.common.core.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;

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
  public static String getIpAddress(HttpServletRequest request) {
      String ip = request.getHeader("X-Real-IP");
      if (!StringUtil.isBlank(ip) && !"unknown".equalsIgnoreCase(ip))
      {
          return ip;
      }
      ip = request.getHeader("X-Forwarded-For");
      if (!StringUtil.isBlank(ip) && !"unknown".equalsIgnoreCase(ip))
      {
          // 多次反向代理后会有多个IP值，第一个为真实IP。
          int index = ip.indexOf(',');
          if (index != -1)
          {
              return ip.substring(0, index);
          }
          else
          {
              return ip;
          }
      }
      else
      {
          return request.getRemoteAddr();
      }
  }

}