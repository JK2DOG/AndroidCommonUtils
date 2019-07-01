package com.jk2dog.common.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.socks.library.KLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ZC on 2017/4/1.
 */

public class DateUtils {
    public static final SimpleDateFormat DATE_FORMAT_DATETIME = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT_YEAR_MONTH_DAY = new SimpleDateFormat(
        "yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");


    public static String getDateTime() {
        return DATE_FORMAT_DATETIME.format(System.currentTimeMillis());
    }


    public static Date Str2Date(String str) {
        try {
            Date date = DATE_FORMAT_YEAR_MONTH_DAY.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }


    /**
     * 得到二个日期间的间隔小时数
     */
    public static String getTwoHour(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long hour = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            hour = (mydate.getTime() - date.getTime()) / (60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        if (hour <= 24) {
            return hour + "";
        }
        return "";
    }


    /**
     * 得到当前时间和截止日期的时间差
     */
    public static String getBeforeHourOfToday(String sj1) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long hour = 0;
        try {
            Date today = new Date();
            Date mydate = myFormatter.parse(sj1);
            hour = (mydate.getTime() - today.getTime()) / (60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        if (hour <= 24) {
            return hour + "";
        }
        return "";
    }


    /**
     * 判断日期跟今天比是否已过期
     *
     * @return 1已过期  0相等  -1没过期
     */
    public static int getOverdueDateToToday(String sj1) {
        int i = 0;
        Date today = new Date();
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = myFormatter.parse(sj1);
            return today.compareTo(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return i;
    }


    /**
     * 得到今天的时间
     *
     * @return 字符串 yyyy-MM-dd
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 判断是否为今天
     */
    public static boolean isToday(String validateDate) {
        if (!TextUtils.isEmpty(validateDate)) {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            if (validateDate.equals(dateString)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * 判断是否为除去今天的一周内
     *
     * @param validateDate type:1--7天之内的
     * type:2--7天之外的
     */
    public static boolean getDayDiffFromToday(String validateDate) {
        Date vDate;
        try {
            vDate = DATE_FORMAT_YEAR_MONTH_DAY.parse(validateDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(today);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -6);  //设置为6天前
        Date before7days = calendar.getTime();   //得到7天前的时间
        if (before7days.getTime() < vDate.getTime()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 将string 按指定格式转化为java.util.Date
     *
     * @throws ParseException
     */
    public static Date str2Date(String str, String format)
        throws ParseException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }


    /**
     *
     * @param str
     * @param sdf
     * @return
     */
    public static Date strToDate(String str, SimpleDateFormat sdf) {
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            KLog.e(e.toString());
        }
        return date;
    }


    /**
     * Calculate days in month int.
     *
     * @param year the year
     * @param month the month
     * @return the int
     */
    public static int calculateDaysInMonth(int year, int month) {
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] bigMonths = { "1", "3", "5", "7", "8", "10", "12" };
        String[] littleMonths = { "4", "6", "9", "11" };
        List<String> bigList = Arrays.asList(bigMonths);
        List<String> littleList = Arrays.asList(littleMonths);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (bigList.contains(String.valueOf(month))) {
            return 31;
        } else if (littleList.contains(String.valueOf(month))) {
            return 30;
        } else {
            if (year <= 0) {
                return 29;
            }
            // 是否闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                return 29;
            } else {
                return 28;
            }
        }
    }


    /**
     * 月日时分秒，0-9前补0
     *
     * @param number the number
     * @return the string
     */
    @NonNull
    public static String fillZero(int number) {
        return number < 10 ? "0" + number : "" + number;
    }

}
