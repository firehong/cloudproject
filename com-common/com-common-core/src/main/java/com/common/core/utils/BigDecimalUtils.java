package com.common.core.utils;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {

    public static final int DEFAULT_SCALE = 2;

    public static BigDecimal format(BigDecimal value) {
        if (value == null) {
            value = new BigDecimal(0);
        }
        return value.setScale(DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal format(BigDecimal value, int scale) {
        if (value == null) {
            value = new BigDecimal(0);
        }
        return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


    public static long multiply(BigDecimal value, int m) {
        if (value == null) return 0;
        return value.multiply(new BigDecimal(m)).longValue();
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if(a==null){return BigDecimal.ZERO;}
        return a.multiply(b);
    }

    /**
     * 相乘
     * @param value
     * @param m
     * @return
     */
    public static BigDecimal _multiply(BigDecimal value, int m) {
        if (value == null) return new BigDecimal(0);
        return value.multiply(new BigDecimal(m));
    }

    public static BigDecimal divide(BigDecimal value, int m) {
        if (value == null) return new BigDecimal(0);
        return value.divide(new BigDecimal(m));
    }

    /**
     *  取整返回余值
     * @param value
     * @param m
     * @return
     */
    public static int divide(BigDecimal value,BigDecimal m) {
        if (value == null) return BigDecimal.ZERO.intValue();
        return value.divide(m, 0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static BigDecimal divide(BigDecimal value,BigDecimal m,int scale) {
        if (value == null) return BigDecimal.ZERO;
        return value.divide(m).setScale(scale, RoundingMode.DOWN);
    }

    public static BigDecimal divide(long value, int m) {
        return divide(new BigDecimal(value), m);
    }

    public static BigDecimal add(BigDecimal a,BigDecimal b){
        if(a==null){
            return b;
        }
        if( b== null){
            return  a;
        }
        return a.add(b);
    }

    public static BigDecimal add(String a,String b){
        return add(convert(a),convert(b));
    }

    public static BigDecimal subtract(BigDecimal a,BigDecimal b){
        
        return a.subtract(b);
    }

    /**
     * 	字符串转BigDecimal	(不失精度)
     * @param amount 金额
     * @return	BigDecimal
     */
    public static BigDecimal convert(String amount) {
    	return convert(amount,BigDecimal.ZERO);
    }

    public static BigDecimal convert(String amount,BigDecimal defaultAmount) {
        if(StringUtil.isBlank(amount)) {
            return defaultAmount;
        }
        BigDecimal val=new BigDecimal(amount);
        return val;
    }

    public static String convertStr(BigDecimal decimal){
        if(decimal == null) return BigDecimal.ZERO.toString();
        return decimal.stripTrailingZeros().toPlainString();
    }

    /**
     * 比较大小
     * @param max
     * @param min
     * @return 0=相等 1=大于 -1=小于
     */
    public static int compare(BigDecimal max,String min){
       BigDecimal minx=convert(min);
       return compare(max,minx);
    }

    /**
     * 比较大小
     * @param max
     * @param min
     * @return 0=相等 1=大于 -1=小于
     */
    public static int compare(BigDecimal max,BigDecimal min){
        return max.compareTo(min);
    }

    /**
     * 在max-min之间 v>min & v<=max
     * @param v 要比较的值
     * @param max 最大值
     * @param min 最小值
     * @return
     */
    public static boolean compare(BigDecimal v,BigDecimal max,BigDecimal min){
        if(v.compareTo(min)==1 && v.compareTo(max)<=0){
            return true;
        }
        return false;
    }

    public static  int compare(BigDecimal source,int target){
        BigDecimal targetDecimal=new BigDecimal(target);
        return source.compareTo(targetDecimal);
    }

    /**
     * 比较大小 若 max大 则返回true,max小 则抛出异常
     * @param max
     * @param min
     * @return
     */
    public static boolean compareBigThrowEx(BigDecimal max,BigDecimal min){
        int flag=compare(max,min);
        return flag != -1;
    }

    /**
     * 金额取反
     * @param money
     * @return
     */
    public static BigDecimal negate(BigDecimal money){
        if(money == null) return BigDecimal.ZERO;
        return  money.negate();//取反
    }

    /**
     * 取整数部分
     * @param a
     * @return
     */
    public static BigDecimal roundNumber(BigDecimal a){
        if(a==null)return BigDecimal.ZERO;
        return a.setScale(0,RoundingMode.DOWN);
    }

    /**
     * 取小数部分
     * 原数据-整数部分=小数部分
     * @param a
     * @return
     */
    public static BigDecimal roundDecimal(BigDecimal a){
        if(a==null)return BigDecimal.ZERO;
        return a.subtract(roundNumber(a));
    }

    public static BigDecimal mbs(BigDecimal money){
            return money.abs();
    }

    public static void main(String[] args) {

        System.out.println(BigDecimal.valueOf(102.00022).intValue());

        System.out.println(roundNumber(BigDecimal.valueOf(102.50022)));
        System.out.println(roundNumber(BigDecimal.valueOf(0)));
        System.out.println(roundNumber(null));
        System.out.println(roundNumber(BigDecimal.valueOf(0.60022)));
        System.out.println(roundNumber(BigDecimal.valueOf(-1.4)));

        System.out.println(roundDecimal(BigDecimal.valueOf(102.50022)));
        System.out.println(roundDecimal(BigDecimal.valueOf(0)));
        System.out.println(roundDecimal(null));
        System.out.println(roundDecimal(BigDecimal.valueOf(0.60022)));
        System.out.println(roundDecimal(BigDecimal.valueOf(-1.4)));


    }
}
