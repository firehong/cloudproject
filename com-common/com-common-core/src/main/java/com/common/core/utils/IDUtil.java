package com.common.core.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @auther Macro
 * @date 2019/11/13 14:15
 * @Description 单号生成工具类
 */
public class IDUtil {

	/**
	 * 锁对象，可以为任意对象
	 */
	private static final Object lockObj =new Object();
	/**
	 * 	订单号生成计数器
	 */
	private static AtomicLong orderNumCount =new AtomicLong(0);
	private static final int MAX_COUNTER_NUM=1000;

	/**
	 * 生存带前缀的序号
	 * @param PR
	 * @return
	 */
	public static String makeOrderNoWithPR(String PR){
		return PR.concat(makeOrderNum());
	}

	/**
	 * 	生成非重复订单号，理论上限1毫秒1000个，可扩展
	 */
	public static String makeOrderNum() {
		// 最终生成的订单号
		StringBuilder orderNum = new StringBuilder();
		try {
			String finOrderNum;
			synchronized (lockObj) {
				// 取系统当前时间作为订单号变量前半部分，精确到毫秒
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
				long nowLong = Long.parseLong(LocalDateTime.now().format(dtf));
				// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
				if (orderNumCount.get() > MAX_COUNTER_NUM) {
					orderNumCount =new AtomicLong(0);
				}
				//组装订单号
				String countStr = MAX_COUNTER_NUM + orderNumCount.getAndIncrement() + "";
				finOrderNum = nowLong + countStr.substring(1);
				// 混淆订单号
				String ymd = finOrderNum.substring(0, 8);
				String subFinOrderNum = finOrderNum.substring(8);
				orderNum.append(ymd);
				orderNum.append(subFinOrderNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderNum.toString();
	}


	/**
	 * @date 2020/4/25 10:32
	 * @Description 获取邀请码
	 * @Param
	 */
	public static String getInviteCode(){
		String randString = "0123456789abcdefghijklmnopqrstuvwxyz";//随机产生的字符串
		String inviteCode = "";
		for(int i=0;i<6;i++){
			inviteCode += String.valueOf(randString.charAt(new Random().nextInt(randString.length())));
		}
		return inviteCode;
	}

	/**
	 * @date 2020/1/6 20:03
	 * @Description 随机字符串
	 * @Param
	 */
	public static String getRandom(int nums){
		return RandomStringUtils.randomAlphanumeric(nums);
	}

	public static void main(String[] args) {
		System.out.println(makeOrderNum());
	}

}
