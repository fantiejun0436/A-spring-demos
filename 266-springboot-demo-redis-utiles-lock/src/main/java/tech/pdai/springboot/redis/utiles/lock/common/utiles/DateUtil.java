package tech.pdai.springboot.redis.utiles.lock.common.utiles;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
* 日期工具类
* @author: zuochao_wang
* @date : 2023/8/22 14:36
*/
public class DateUtil {

    public static final String DATE_FORMAT_14 = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_12 = "yyyyMMddHHmm";

    private DateUtil(){

    }

    /**
     * @Title: formatTime
     * @Description: 毫秒转化时分秒毫秒
     * @param ms
     * @return
     */
    public static String formatTime ( Long ms )
    {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuilder sb = new StringBuilder();
        if (day > 0)
        {
            sb.append(day + "天");
        }
        if (hour > 0)
        {
            sb.append(hour + "小时");
        }
        if (minute > 0)
        {
            sb.append(minute + "分");
        }
        if (second > 0)
        {
            sb.append(second + "秒");
        }
        return sb.toString();
    }

    /**
     * @Title: formatTime
     * @Description: 秒转化时分秒
     * @param seconds
     * @return
     */
    public static String formatTimeSecond( Long seconds )
    {
        long ss = 1;
        long mi = ss * 60;
        long hh = mi * 60;
        long dd = hh * 24;

        long day = seconds / dd;
        long hour = (seconds - day * dd) / hh;
        long minute = (seconds - day * dd - hour * hh) / mi;
        long second = (seconds - day * dd - hour * hh - minute * mi) / ss;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        return sb.toString();
    }

    /**
     * 增加月份
     * @param date 日期
     * @param count 月份数
     */
    public static Date addMonths(Date date, int count){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, count);
        return calendar.getTime();
    }

    /**
     * 增加天数
     * @param date 日期
     * @param days 天数
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }

    /**
     * 增加小时
     * @param date 日期
     * @param count 小时数
     */
    public static Date addHours(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, count);
        return calendar.getTime();
    }

    /**
     * 增加分钟
     * @param date 日期
     * @param count 分钟数
     */
    public static Date addMinutes(Date date, int count){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, count);
        return calendar.getTime();
    }

    /**
     * 获取当前日期处于一周的周几
     * @param date 日期
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day != 1 ? day - 1 : 7;
    }

    /**
     * 获取当前日期在所处月份的总天数
     * @param date 日期
     */
    public static int getMonthTotalDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前日期在所处月份中的第几天
     * @param date 日期
     */
    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一个时间范围内的所有日期
     * @param fromDate 日期
     */
    public static List<Date> getBetweenDates(Date fromDate, Date toDate) {
        List<Date> dates = new ArrayList<>();

        while (fromDate.getTime() <= toDate.getTime()) {
            dates.add(fromDate);
            fromDate = DateUtil.addDays(fromDate, 1);
        }
        return dates;
    }

    /**
     * 获取14位时间戳
     * @return
     */
    public static String get14timeStr(String businessSystemCode) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_14);
        Date date = new Date();
        return businessSystemCode+"_"+sdf.format(date);
    }

    /**
     * 获取14位时间戳
     * @return
     */
    public static String get14timeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_14);
        Date date = new Date();
        return sdf.format(date);
    }
    /**
     * 获取12位时间戳
     * @return
     */
    public static String get12timeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_12);
        Date date = new Date();
        return sdf.format(date);
    }

}