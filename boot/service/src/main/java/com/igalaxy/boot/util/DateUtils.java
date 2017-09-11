package com.igalaxy.boot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jinlong on 16/8/8.
 */
public class DateUtils {
    /**
     * 日期格式 MM月dd日
     */
    public static final String SIMPLE_DATE_FORMAT = "MM月dd日";
    /**
     * 日期格式 yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期时间格式 yyyy-MM-dd HH:mm
     */
    public static final String DEFAULT_DATETIME_FORMAT2 = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_DATETIME_FORMAT3 = "yyyy/MM/dd HH:mm:ss";
    /**
     * 日期时间格式 yyyy-MM-dd HH24:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT4 = "yyyy-MM-dd HH24:mm:ss";
    /**
     * 日期时间格式 yyyy-MM-dd HH24:mm
     */
    public static final String DEFAULT_DATETIME_FORMAT5 = "yyyy-MM-dd HH24:mm";
    /**
     * oracle 字符串转日期格式
     */
    public static final String DEFAULT_DATETIME_FORMAT6 = "YYYY-MM-DD:HH24:MI:SS";
    /**
     * 时间格式 HH:mm:ss
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String OTHER_TIME_FORMAT = "HH:mm";

    /**
     * 每天小时数
     */
    private static final long HOURS_PER_DAY = 24;
    /**
     * 每小时分钟数
     */
    private static final long MINUTES_PER_HOUR = 60;
    /**
     * 每分钟秒数
     */
    private static final long SECONDS_PER_MINUTE = 60;
    /**
     * 每秒的毫秒数
     */
    private static final long MILLIONSECONDS_PER_SECOND = 1000;
    /**
     * 每分钟毫秒数
     */
    private static final long MILLIONSECONDS_PER_MINUTE = MILLIONSECONDS_PER_SECOND * SECONDS_PER_MINUTE;
    /**
     * 每天毫秒数
     */
    private static final long MILLIONSECONDS_SECOND_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE
            * MILLIONSECONDS_PER_SECOND;

    public static TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

    private DateUtils() {
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为日期对象
     *
     * @param date
     *            待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date) {
        return getDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为日期对象
     *
     * @param date
     *            待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDateTime(String date) {
        if (StringUtils.isNotBlank(date)) {
            date = date.replaceAll("/", "-");
            return getDate(date, DEFAULT_DATETIME_FORMAT1, null);
        } else {
            return null;
        }

    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date
     *            待转换字符串
     * @param format
     *            日期格式
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date, String format) {
        return getDate(date, format, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date
     *            日期对象
     * @param format
     *            日期格式
     * @param defVal
     *            转换失败时的默认返回值
     * @return 转换后的日期对象
     */
    public static Date getDate(String date, String format, Date defVal) {
        Date d;
        try {
            d = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            d = defVal;
        }
        return d;
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd格式的字符串
     *
     * @param date
     *            待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date
     *            待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String forDatetime(Date date) {
        if (date != null) {
            return formatDate(date, DEFAULT_DATETIME_FORMAT1, null);
        } else {
            return null;
        }

    }

    /**
     * 将日期对象格式化成HH:mm:ss格式的字符串
     *
     * @param date
     *            待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatTime(Date date) {
        return formatDate(date, DEFAULT_TIME_FORMAT, null);
    }

    /**
     * 将日期对象格式化成HH:mm:ss格式的字符串
     *
     * @param date
     *            待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatTime(Date date, String format) {
        return formatDate(date, format, null);
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date
     *            待格式化日期对象
     * @param format
     *            格式化格式
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date, String format) {
        return formatDate(date, format, null);
    }

    /**
     * 带时区的格式化时间
     *
     * @param date
     * @param format
     * @param timeZone
     * @return
     */
    public static String formatDateTimeZone(Date date, String format, TimeZone timeZone) {
        String ret = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(timeZone);
            ret = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date
     *            待格式化日期对象
     * @param format
     *            格式化格式
     * @param defVal
     *            格式化失败时的默认返回空
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date, String format, String defVal) {
        String ret;
        try {
            ret = new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            ret = defVal;
        }
        return ret;
    }

    /**
     * 日期加减天数 返回指定日期加上days天后的日期
     *
     * @param date
     *            如果为空则默认为当前时间
     * @param days
     *            为正数：加天，负数：减天
     * @return
     */
    public static Date plusDays(Date date, int days) {
        if (date == null)
            date = getToday();
        return changeDays(date, days);
    }

    /**
     * 日期加减小时 返回指定日期加减上小时后的日期
     *
     * @param date
     *            如果为空则默认为当前时间
     * @param hours
     *            为正数：加小时，负数：减小时
     * @return
     */
    public static Date plusHours(Date date, int hours) {
        if (date == null)
            date = getToday();
        return changeHours(date, hours);
    }

    /**
     * 分钟加减 返回指定日期加减上分钟后的日期
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date plusMinute(Date date, int minutes) {
        if (date == null)
            date = getToday();
        return changeMinute(date, minutes);
    }

    /**
     * 月份加减 返回指定日期加减上月数后的日期
     *
     * @param date
     * @return
     */
    public static Date plusMonth(Date date, int months) {
        if (date == null)
            date = getToday();
        return changeMonth(date, months);
    }

    /**
     * 日期加减年数 返回指定日期加减上年数后的日期
     *
     * @param date
     *            如果为空则默认为当前时间
     * @return
     */
    public static Date plusYear(Date date, int years) {
        if (date == null)
            date = getToday();
        return changeYear(date, years);
    }

    /**
     * 获取当前日期加时间
     *
     * @return
     */
    public static Date getToday() {
        return new Date();
    }

    /**
     * 当前时间的毫秒数
     *
     * @return
     */
    public static long currentTimeMillis() {
        return getToday().getTime();
    }

    /**
     * 获得当前时间sql.date
     */
    public static java.sql.Date getTodaySqlDate() {
        return new java.sql.Date(getToday().getTime());
    }

    /**
     * @param date
     *            为空：默认当前时间
     * @param format
     *            ：为空：默认yyyy-MM-dd
     * @return
     */
    public static String getTodayStr(Date date, String format) {
        if (date == null)
            date = getToday();
        if (StringUtils.isBlank(format))
            format = DEFAULT_DATE_FORMAT;
        return formatDate(date, format);
    }

    /**
     * 比较传入日期与当前日期相差的天数
     *
     * @param d
     * @return
     */
    // public static int intervalDay(Date d) {
    // return intervalDay(getToday(), d);
    // }

    /**
     * 比较两个日期相差的天数 D1-D2
     *
     * @param d1
     *            为空：默认为当前时间
     * @param d2
     * @return
     */
    public static int intervalDay(Date d1, Date d2) {
        if (d1 == null)
            d1 = getToday();
        long intervalMillSecond = setToDayStartTime(d1).getTime() - setToDayStartTime(d2).getTime();
        // 相差的天数 = 相差的毫秒数 / 每天的毫秒数 (小数位采用去尾制)
        return (int) (intervalMillSecond / MILLIONSECONDS_SECOND_PER_DAY);
    }

    /**
     * 获得两个日期之间相差的分钟数。（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return 返回两个日期之间相差的分钟数值
     */
    public static int intervalMinutes(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        // 相差的分钟数 = 相差的毫秒数 / 每分钟的毫秒数 (小数位采用进位制处理，即大于0则加1)
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_MINUTE
                + (intervalMillSecond % MILLIONSECONDS_PER_MINUTE > 0 ? 1 : 0));
    }

    /**
     * 获得两个日期之间相差的秒数差（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int intervalSeconds(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        return (int) (intervalMillSecond / MILLIONSECONDS_PER_SECOND
                + (intervalMillSecond % MILLIONSECONDS_PER_SECOND > 0 ? 1 : 0));
    }

    /**
     * 将时间调整到当天0:0:0
     *
     * @param date
     * @return
     */
    public static Date setToDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 判断当前时间
     *
     * @return
     */
    public static String getDateStatus() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 12) {
            return "morning";
        } else if (hour >= 12 && hour < 18) {
            return "noon";
        } else if (hour >= 18 && hour < 24) {
            return "evning";
        } else {
            return "midnight";
        }
    }

    public static int getAge(Date birthday) {
        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);
        // 取得生日年份
        int year = birth.get(Calendar.YEAR);
        // 年龄
        int age = now.get(Calendar.YEAR) - year;
        // 修正
        now.set(Calendar.YEAR, year);
        age = (now.before(birth)) ? age - 1 : age;
        return age;
    }

    /**
     * d1 和 d2 是否同一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDate(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(d1.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(d2.getTime());

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断是否d2是d1的后一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isContinueDay(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        if (intervalDay(d1, d2) == 1)
            return true;
        return false;
    }

    /**
     * 得到没有时间的日期
     *
     * @param date
     * @return
     */
    public static Date truncDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 得到没有分和秒的时间
     *
     * @param date
     * @return
     */
    public static Date truncDateHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 得到旬.
     *
     * @param input
     * @return
     * @author <a href="mailto:wangxin@knet.cn">北京王欣</a>
     */
    public static String getCnDecade(Date input) {
        String day = formatDate(input);
        String decade = day.replaceAll("01日", "上旬").replaceAll("11日", "中旬").replaceAll("21日", "下旬");
        return decade;
    }

    public static Date getTodayZero() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getTheDayBefore(Date date) {
        return new Date(date.getTime() - (long) 24 * (long) 60 * 60 * 1000);
    }

    public static Date[] getTenDayBefore() {// 计算之前一旬的起止时间
        Date[] ret = new Date[2];
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);// 0点0分0秒
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {// 今天处在某月的上旬，起始时间是前一个月的21号，终止时间是本月的1号
            c.set(Calendar.DAY_OF_MONTH, 1);// 本月的1号
            ret[1] = new Date(c.getTime().getTime());
            c.setTime(getTheDayBefore(c.getTime()));// 往前翻一天，到上一个月
            c.set(Calendar.DAY_OF_MONTH, 21);
            ret[0] = new Date(c.getTime().getTime());
        } else {//

            if (10 < day && day <= 20) {// 今天处在某月的中旬，起始时间是本月的1号，终止时间是本月的11号
                c.set(Calendar.DAY_OF_MONTH, 1);
                ret[0] = new Date(c.getTime().getTime());
                c.set(Calendar.DAY_OF_MONTH, 11);
                ret[1] = new Date(c.getTime().getTime());
            } else {// 今天处在某月的下旬，起始时间是本月的11号，终止时间是本月的21号
                c.set(Calendar.DAY_OF_MONTH, 11);
                ret[0] = new Date(c.getTime().getTime());
                c.set(Calendar.DAY_OF_MONTH, 21);
                ret[1] = new Date(c.getTime().getTime());
            }
        }
        return ret;
    }

    /**
     * 计算某个输入时间的当前旬起止时间
     *
     * @param input
     * @return
     */
    public static Date[] getCurrentTenDay(Date input) {// 计算某个输入时间的当前旬起止时间
        Date[] ret = new Date[2];
        Calendar c = Calendar.getInstance();
        c.setTime(input);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);// 0点0分0秒
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {// 今天处在某月的上旬，起始时间是本月的1号，终止时间是本月的11号
            c.set(Calendar.DAY_OF_MONTH, 1);// 本月的1号
            ret[0] = new Date(c.getTime().getTime());
            c.set(Calendar.DAY_OF_MONTH, 11);
            ret[1] = new Date(c.getTime().getTime());
        } else {//

            if (10 < day && day <= 20) {// 今天处在某月的中旬，起始时间是本月的11号，终止时间是本月的21号
                c.set(Calendar.DAY_OF_MONTH, 11);
                ret[0] = new Date(c.getTime().getTime());
                c.set(Calendar.DAY_OF_MONTH, 21);
                ret[1] = new Date(c.getTime().getTime());
            } else {// 今天处在某月的下旬，起始时间是本月的21号，终止时间是下个月的1号
                c.set(Calendar.DAY_OF_MONTH, 21);
                ret[0] = new Date(c.getTime().getTime());
                ret[1] = getNextMonthFirst(c.getTime());
            }
        }
        return ret;
    }

    public static Date getNextMonthFirst(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);// 0点0分0秒
        c.add(Calendar.MONTH, 1);// 加一个月
        c.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        return c.getTime();
    }

    public static Date[] getTheMonthBefore(Date date) {// 计算之前一旬的起止时间
        Date[] ret = new Date[2];
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);// 0点0分0秒
        c.set(Calendar.DAY_OF_MONTH, 1);// 本月的1号
        ret[1] = new Date(c.getTime().getTime());
        c.setTime(getTheDayBefore(c.getTime()));// 往前翻一天，到上一个月
        c.set(Calendar.DAY_OF_MONTH, 1);// 上月的1号
        ret[0] = new Date(c.getTime().getTime());
        return ret;
    }

    /**
     * 获取当前季度 getCurrentQuarter
     *
     * @return Integer
     * @throws @since
     *             1.0.0
     */
    public static Integer getCurrentQuarter() {
        int month = Integer.parseInt(DateUtils.formatDate(new Date(), "MM"));
        int quarter = 0;
        if (month >= 1 && month <= 3) {
            quarter = 1;
        } else if (month >= 4 && month <= 6) {
            quarter = 2;
        } else if (month >= 7 && month <= 9) {
            quarter = 3;
        } else if (month >= 10 && month <= 12) {
            quarter = 4;
        }
        return quarter;
    }

    /**
     * QuarterToYearMonthDay
     *
     * @param year
     * @param quarter
     * @return Map<String,String>
     * @throws @since
     *             1.0.0
     */
    public static Map<String, String> getQuarterToYearMonthDay(Integer year, Integer quarter) {
        if (year != null && year > 0 && quarter != null && quarter > 0) {
            Map<String, String> map = new HashMap<String, String>();
            if (quarter == 1) {
                map.put("startTime", year + "-01-" + getMonthDays(year, 1) + " 00:00:00");
                map.put("endTime", year + "-03-" + getMonthDays(year, 3) + " 23:59:59");
            } else if (quarter == 2) {
                map.put("startTime", year + "-04-" + getMonthDays(year, 4) + " 00:00:00");
                map.put("endTime", year + "-06-" + getMonthDays(year, 6) + " 23:59:59");
            } else if (quarter == 3) {
                map.put("startTime", year + "-07-" + getMonthDays(year, 7) + " 00:00:00");
                map.put("endTime", year + "-09-" + getMonthDays(year, 9) + " 23:59:59");
            } else if (quarter == 4) {
                map.put("startTime", year + "-10-" + getMonthDays(year, 10) + " 00:00:00");
                map.put("endTime", year + "-12-" + getMonthDays(year, 12) + " 23:59:59");
            }
            return map;
        }
        return null;
    }

    /**
     * 根据指定年月，获取月的天数 getMonthDays
     *
     * @param year
     * @return Integer
     * @throws @since
     *             1.0.0
     */
    public static Integer getMonthDays(Integer year, Integer month) {
        if (year != null && year > 0 && month != null && month > 0) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DATE, 1);
            c.add(Calendar.DATE, -1);
            return c.get(Calendar.DATE);
        }
        return 0;
    }

    /**
     * 计算两个时间点的差，以文本形式返回（如：1分钟前，2小时前，3天前），两个参数可以自动识别出较早的和较晚的
     *
     * @param date1
     * @param date2
     * @return
     */
    public static String getTimeDiffText(Date date1, Date date2) {
        long diff = Math.abs(date1.getTime() - date2.getTime()) / 1000;
        long minuteSeconds = 60;
        long hourSeconds = minuteSeconds * 60;
        long daySeconds = hourSeconds * 24;
        long weekSeconds = daySeconds * 7;
        Date min = date1.compareTo(date2) < 0 ? date1 : date2;
        // 时间差超过一周时，返回具体日期即可
        // long monthSeconds = daySeconds * 30;
        // long yearSeconds = monthSeconds * 12;
        // if (diff >= yearSeconds) {
        // return diff / yearSeconds + "年前";
        // } else if (diff >= monthSeconds) {
        // return diff / monthSeconds + "月前";
        if (diff >= weekSeconds) {
            return formatDate(min);
        } else if (diff >= daySeconds) {
            return diff / daySeconds + "天前";
        } else if (diff >= hourSeconds) {
            return diff / hourSeconds + "小时前";
        } else if (diff >= minuteSeconds) {
            return diff / minuteSeconds + "分钟前";
        } else {
            return diff + "秒前";
        }
    }

    /**
     * 获取当前时间是星期几
     *
     * @param dt
     * @return
     */
    public static int getWeek(Date dt) {

        int[] week = { 7, 1, 2, 3, 4, 5, 6 };
        // String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
        // "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return week[w];
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate(String datePattern) {

        try {
            return new SimpleDateFormat(datePattern).parse(getCurrentDateByString(datePattern));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 以字符串形式获取当前日期
     *
     * @return
     */
    public static String getCurrentDateByString(String datePattern) {

        return new SimpleDateFormat(datePattern).format(System.currentTimeMillis());
    }

    public static String getCurrentDateByString(Date date, String datePattern) {

        return new SimpleDateFormat(datePattern).format(date);
    }

    public static boolean beforeDate(Date date1, Date date2) {

        return date1.before(date2);
    }

    public static boolean beforeDate(String date1, String date2) {

        Date dt1 = null, dt2 = null;
        dt1 = getDateTime(date1);
        dt2 = getDateTime(date2);
        return beforeDate(dt1, dt2);
    }

    /**
     * 比较时间是否在一个指定时间范围之内
     *
     * @param date
     * @param from
     * @param end
     * @return
     */
    public static boolean betweenDateScope(String date, String from, String end) {

        if (date == null || from == null || end == null)
            return false;
        return (!beforeDate(date, from) && beforeDate(date, end));
    }

    /**
     * 比较时间是否在一个指定时间范围之内
     *
     * @param date
     * @param from
     * @param end
     * @return
     */
    public static boolean betweenDateScope(Date date, Date from, Date end) {

        if (date == null || from == null || end == null)
            return false;
        return (!beforeDate(date, from) && beforeDate(date, end));
    }

    /**
     * 判断时间范围
     *
     * @param time
     * @param startRange
     * @param endRange
     * @return
     */
    public static boolean checkTimeRange(String time, String startRange, String endRange) {

        String[] s = startRange.split(":");
        int totalStart = (Integer.parseInt(s[0]) * 3600) + (Integer.parseInt(s[1]) * 60) + Integer.parseInt(s[2]);
        String[] e = endRange.split(":");
        int totalEnd = (Integer.parseInt(e[0]) * 3600) + (Integer.parseInt(e[1]) * 60) + Integer.parseInt(e[2]);

        String[] t = time.split(":");
        int timeTotal = (Integer.parseInt(t[0]) * 3600) + (Integer.parseInt(t[1]) * 60) + Integer.parseInt(t[2]);
        return (timeTotal >= totalStart && timeTotal <= totalEnd);
    }

    /**
     * 指定日期时间分钟上加上分钟数
     *
     * @param date
     * @param minutes
     * @return
     */
    private static Date changeMinute(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 指定日期时间上加上时间数
     *
     * @param date
     * @param hours
     * @return
     */
    private static Date changeHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * 指定的日期加减天数
     *
     * @param date
     * @param days
     * @return
     */
    private static Date changeDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    /**
     * 指定的日期加减年数
     *
     * @param date
     * @param years
     * @return
     */
    private static Date changeYear(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * 指定的日期加减月数
     *
     * @param date
     * @return
     */
    private static Date changeMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 获取当前日期的开始时间 2015-1-25 00:00:00
     *
     * @return
     */
    public static Date getCurrentDayBegin() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        // calendar.add(Calendar.DATE, -4);
        // System.out.println(DateUtils.forDatetime(calendar.getTime()));
        return calendar.getTime();
    }

    /**
     * 获取当天结束时间 2015-1-25 23:59:59
     *
     * @return
     */
    public static Date getCurrentDayEnd() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取昨天日期的开始时间 2015-1-24 00:00:00
     *
     * @return
     */
    public static Date getLastDayBegin() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1);// 前一天
        return calendar.getTime();
    }



    /**
     * 获取昨天日期的结束时间 2015-1-24 23:59:59
     *
     * @return
     */
    public static Date getLastDayEnd() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.add(Calendar.DATE, -1);// 前一天
        return calendar.getTime();
    }

    public   static   Date   getLastDayOfMonth(Date   sDate1)   {
        Calendar   cDay1   =   Calendar.getInstance();
        cDay1.setTime(sDate1);
        final   int   lastDay   =   cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date   lastDate   =   cDay1.getTime();
        lastDate.setDate(lastDay);
        return   lastDate;
    }

    /**
     * 获取前30天的开始时间 2014-12-24 00:00:00
     *
     * @return
     */
    public static Date getLastMonthDayBegin() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -30);// 前30天
        return calendar.getTime();
    }

    public static Date getBeginTime() {// 5年内
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.YEAR, -5);// 默认是当前系统5年
        return calendar.getTime();
    }

    public static boolean isEqual(Date d1, Date d2) {
        if (d1 == d2) {
            return true;
        } else if (d1 != null && d2 != null) {
            return d1.getTime() == d2.getTime();
        }
        return false;
    }

    public static boolean isNotEqual(Date d1, Date d2) {
        return !isEqual(d1, d2);
    }

    public static String convertTimeToFormat(Date date) {
        long timeStamp = date.getTime() / 1000l;
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 2) {
            return "昨天";
        } else if (time > 0) {
            return formatDate(date);
        } else {
            return "刚刚";
        }
    }

    public static void main(String[] args) {
        //System.out.println(DateUtils.forDatetime(DateUtils.truncDate(new Date())));

        int d =  DateUtils.intervalDay(DateUtils.getDate("2016-06-08"), DateUtils.getDate("2016-06-06") );
        System.out.println(d);
    }
}
