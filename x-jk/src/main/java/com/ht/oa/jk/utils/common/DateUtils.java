package com.ht.oa.jk.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: gaokb
 * Date: 18-4-10
 * Time: 上午11:02
 * To change this template use File | Settings | File Templates.
 */
public final class DateUtils {

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * long时间转化为 date
     */
    public static Date longToDate(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * yyyy-MM-dd HH:mm:ss转换为秒
     */
    public static int strDateToSecond(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return (int) (format.parse(str).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * int类型 yyyy-MM-dd的字符类型
     */
    public static String intToStrDateToDay(int second) {
        Date date = new Date(second * 1000l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String intToyyyyMMddHHmmss(int second) {
        try {
            Date date = new Date(second * 1000l);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String intToyyyyMMdd(int second) {
        try {
            Date date = new Date(second * 1000l);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * int to yyyyMMddHHmmss
     */
    public static String intToyyyy(int second) {
        Date date = new Date(second * 1000l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取yyyyMMddHHmmss日期
     */
    public static String getyyyyMMddHHmmss() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * 获取yyyy年MM月dd日 HH:mm:ss
     */
    public static String getyyyyN_MMY_ddr_HHmmss(int second) {
        Date date = new Date(second * 1000L);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取当前时间的字符串表示
     */
    public static String getYMd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    /**
     * 获取当前时间的数字表示
     */
    public static int getYMdToNum() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(new Date());
        return Integer.parseInt(str);
    }

    /**
     * 获取当前时间的字符串表示
     */
    public static String getYMdHms() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取天
     */
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
