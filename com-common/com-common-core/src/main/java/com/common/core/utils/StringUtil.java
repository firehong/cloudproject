package com.common.core.utils;

/**
 * 字符串相关工具类
 * 
 * @author macro  2017年11月28日 下午5:25:39
 * @version share 1.0
 */
public class StringUtil {
    
	
	/**
	 * ****************************************
	 * function: 判断对象是否为空(如果为空,return true)
	 * @param str
	 * @return
	 * 
	 * ========================================
	 * created by wuhong on 2017-1-13下午1:11:04
	 * description: 
	 * ========================================
	 * modified by name on time 
	 * description:
	 * 
	 * 
	 *****************************************
	 */
	public static boolean isEmpty(Object str) {
		if ((str == null) || ("null".equals(str))) 
		{
		      return true;
	    }
	    return String.valueOf(str).length() < 1;		
	}
	
	/**
	 * ****************************************
	 * function: 判断字符串是否为空(空 ,return true)
	 * @param cs
	 * @return
	 * 
	 * ========================================
	 * created by wuhong on 2017-1-13下午1:15:30
	 * description: 
	 * ========================================
	 * modified by name on time 
	 * description:
	 * 
	 * 
	 *****************************************
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0)
		{
			return true;
		}			
		for (int i = 0; i < strLen; i++)
		{
			if (!Character.isWhitespace(cs.charAt(i)))
			{
				return false;
			}
				
		}
		return true;
	}
	
	/**
	 * 判断所有参数是否全为null;
	 * @param args
	 * @return 都为空返回true 
	 */
	public static boolean isAllNUll(Object ...args) {
		boolean flag = true;
		for (int i = 0; i < args.length; i++) {
			if(args[i] instanceof String) {
				String str = (String) args[i];
				if(str!=null&&!str.trim().equals("")) {
					flag = false;
				}
			}else {
				if(args[i]!=null) {
					flag = false;
				}
			}
		}
		return flag;
	}
	
}
