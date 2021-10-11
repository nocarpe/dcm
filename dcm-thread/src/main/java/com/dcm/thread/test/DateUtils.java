package com.dcm.thread.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;



public class DateUtils {

    /** 秒和毫秒的基数. */
    public static final long SECOND_TO_MILLIS_BASE = 1000L;
    /** 一小时对应的毫秒数. */
    public static final long MILLIS_OF_HOUR = 60 * 60 * SECOND_TO_MILLIS_BASE;
    /**
     * 一分钟对应的毫秒数
     */
    public static final long MILLIS_OF_MINUTE = 60 * SECOND_TO_MILLIS_BASE;

    /** 一天对应的毫秒数. */
    public static final long MILLIS_OF_DAY = 24 * MILLIS_OF_HOUR;
    /** 一分钟对应的秒 */
    public static final int SECOND_OF_MILLIS = 60;
    /** 一小时对应的秒 */
    public static final int SECOND_OF_HOUR = 60 * SECOND_OF_MILLIS;
    /** 一天对应的秒 */
    public static final int SECOND_OF_DAY = 24 * SECOND_OF_HOUR;

    public static final String STD_DATE_TIME_TEMPLATE = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String STD_DATE_TIME_NO_BLANK_TEMPLATE = "yyyyMMddHHmmss";

    public static final String DATE_TEMPLATE = "yyyyMMdd";

    public static final String DATE_LOT_TEMPLATE = "yyMMdd";
    public static final String DATE_FT_TEMPLATE = "MM/dd";

    public static final String STD_DATE_TEMPLATE = "yyyy-MM-dd";

    public static final String TIME_FORMAT = "HH:mm";

    public static final String STR_DATE_FORMAT = "yyyy年MM月dd日HH:mm:ss";

    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return floorDay(calendar.getTime());
    }

    /**
     * 抹平时间零头,返回当天零时
     *
     * @param date 时间
     */
    public static Date floorHour(Date date) {
        return floorDay(date);
    }

    /**
     * 返回当天最后时间
     *
     * @param date 时间
     */
    public static Date endDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
            23, 59, 59);
        return calendar.getTime();
    }

    /**
     * 判断当前时间是否大于6点
     */
    public static boolean checkMoreSixClock() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sixClock = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 6, 0, 0);
        if (now.isAfter(sixClock)) {
            return true;
        }
        return false;

    }

    /**
     * 抹平时间零头,返回当天零时
     *
     * @param date 时间
     */
    public static Date floorDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 抹平毫秒
     *
     * @param date 时间
     */
    public static Date floorMillSeconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 时间加上指点天数
     *
     * @param date 时间
     * @param days 天数
     * @return 加上指定天数的时间
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 时间加上指定小时数
     *
     * @param date 时间
     * @param hours 小时数
     */
    public static Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    /**
     * 时间减去指定小时数
     *
     * @param date 时间
     * @param days 天数
     * @return 减去指定天数的时间
     */
    public static Date reduceDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        return calendar.getTime();
    }

    /**
     * 时间减去指定分钟数
     *
     * @param date 时间
     * @param hours 分钟数
     * @return 减去指定小时数的时间
     */
    public static Date reduceHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -hours);
        return calendar.getTime();
    }

    /**
     * 时间减去指定分钟数
     *
     * @param date 时间
     * @param minutes 分钟数
     * @return 加上指定小时数的时间
     */
    public static Date reduceMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -minutes);
        return calendar.getTime();
    }

    /**
     * 时间加上指点小时数
     *
     * @param date 时间
     * @param minutes 分钟数
     * @return 加上指定小时数的时间
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static Date addMillisecond(Date date, int milliSeconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, milliSeconds);
        return calendar.getTime();
    }

    public static Date addSeconds(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 抹平周零头, 返回当周一
     */
    public static LocalDate floorWeek(LocalDate localDate) {
        return localDate.minusDays(localDate.getDayOfWeek().getValue() - 1);
    }

    /**
     * 抹平月零头, 返回当月1号
     */
    public static LocalDate floorMonth(LocalDate localDate) {
        return localDate.minusDays(localDate.getDayOfMonth() - 1);
    }

    /**
     * 抹平年零头, 返回当年1月1号
     */
    public static LocalDate floorYear(LocalDate localDate) {
        return localDate.minusDays(localDate.getDayOfYear() - 1);
    }

    public static long diff(Date fromDate, Date toDate) {
        return fromDate.getTime() - toDate.getTime();
    }

    /**
     * 计算时间差
     */
    public static int diff(Date fromDate, Date toDate, final long unitMillis) {
        long diffMills = fromDate.getTime() - toDate.getTime();
        long diff = diffMills / unitMillis;
        return (int) diff;
    }

    public static double diffDouble(Date fromDate, Date toDate, final long unitMillis) {
        long diffMills = fromDate.getTime() - toDate.getTime();
        return (double) diffMills / unitMillis;
    }

    /**
     * 计算秒差
     */
    public static int diffSecond(Date fromDate, Date toDate) {
        return diff(fromDate, toDate, SECOND_TO_MILLIS_BASE);
    }

    /**
     * 计算时差
     */
    public static int diffHour(Date fromDate, Date toDate) {
        return diff(fromDate, toDate, MILLIS_OF_HOUR);
    }

    /**
     * 计算分钟时差
     */
    public static int diffMinute(Date fromDate, Date toDate) {
        return diff(fromDate, toDate, MILLIS_OF_MINUTE);
    }


    /**
     * 计算日差
     */
    public static int diffDay(Date fromDate, Date toDate) {
        return diff(fromDate, toDate, MILLIS_OF_DAY);
    }


    /**
     * 计算日差
     */
    public static long diffDay(LocalDate fromDate, LocalDate toDate) {
        return ChronoUnit.DAYS.between(fromDate, toDate);
    }

    /**
     * 计算周差
     */
    public static long diffWeek(LocalDate fromDate, LocalDate toDate) {
        return ChronoUnit.WEEKS.between(fromDate, toDate);
    }

    /**
     * 计算月差
     */
    public static long diffMonth(LocalDate fromDate, LocalDate toDate) {
        return ChronoUnit.MONTHS.between(fromDate, toDate);
    }

    /**
     * 计算年差
     */
    public static long diffYear(LocalDate fromDate, LocalDate toDate) {
        return ChronoUnit.YEARS.between(fromDate, toDate);
    }

    /**
     * 抹平周零头，计算周差
     * 注：
     * 日期1 = K周最后一天
     * 日期2 = K+1周第一天
     * 日期1和日期2周差为1
     */
    public static long floorAndDiffWeek(LocalDate fromDate, LocalDate toDate) {
        LocalDate floorFrom = floorWeek(fromDate);
        LocalDate floorTo = floorWeek(toDate);
        return diffWeek(floorFrom, floorTo);
    }

    /**
     * 抹平月零头，计算月差
     * 注：
     * 日期1 = K月最后一天
     * 日期2 = K+1月第一天
     * 日期1和日期2月差为1
     */
    public static long floorAndDiffMonth(LocalDate fromDate, LocalDate toDate) {
        LocalDate floorFrom = floorMonth(fromDate);
        LocalDate floorTo = floorMonth(toDate);
        return diffMonth(floorFrom, floorTo);
    }

    public static long floorAndDiffYear(LocalDate fromDate, LocalDate toDate) {
        LocalDate floorFrom = floorYear(fromDate);
        LocalDate floorTo = floorYear(toDate);
        return diffYear(floorFrom, floorTo);
    }

    /**
     * 将毫秒时间戳转换为Date
     * （去掉校验了，此方法慎用）
     *
     * @param millis 毫秒时间戳
     * @return Date对象
     */
    public static Date millis2Date(Long millis) {
        if (null == millis) {
            return null;
        }
        return new Date(millis);
    }




    /**
     * 格式化日期.
     *
     * @param date 日期
     * @param pattern 格式化模板
     * @return 格式化字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 格式化日期与时间，格式为：yyyy-MM-dd HH:mm:ss.
     */
    public static String formatDateTimeSTD(Date date) {
        return format(date, STD_DATE_TIME_TEMPLATE);
    }

    /** 格式化日期与时间，格式为：yyyyMMddHHmmss */
    public static String formatDateTimeSTDNoBlank(Date date) {
        return format(date, STD_DATE_TIME_NO_BLANK_TEMPLATE);
    }

    /**
     * 格式化日期，格式为：yyyyMMdd.
     */
    public static String formatDate(Date date) {
        return format(date, DATE_TEMPLATE);
    }

    /**
     * 获取指定时间的当前月份第一天开始时间
     */
    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取指定时间的当前月份最后一天开始时间
     */
    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取当前时间点过去七天的时间
     */
    public static Date getNearlyOneMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        return c.getTime();
    }

    /**
     * 将日期转换为秒级时间戳.
     *
     * @param date 日期对象
     * @return 秒级时间戳
     */
    public static long getSeconds(Date date) {
        if (null == date) {
            return 0;
        }
        return (int) (date.getTime() / SECOND_TO_MILLIS_BASE);
    }


    public static int currentSeconds() {
        return (int) (System.currentTimeMillis() / SECOND_TO_MILLIS_BASE);
    }


    public static Long getISOSeconds(Date date) {
        if (null == date) {
            return 0L;
        }
        return date.getTime() / SECOND_TO_MILLIS_BASE;
    }


    public static Date ISOTimeStampToDate(Long millis) {
        if (millis == null) {
            return null;
        }
        return new Date(millis * 1000);
    }


    /**
     * 获取前一天
     */
    public static Date getDayBefore(Date date) {
        return getDayBefore(date, 1);
    }

    /**
     * 获取前N天
     */
    public static Date getDayBefore(Date date, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -day);
        return calendar.getTime();
    }

    public static int getDayBeforeDayValue(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayValue(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 0);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间点过去7天的时间
     */
    public static Date getNearlySevenDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.HOUR_OF_DAY, -7);
        return c.getTime();
    }



    /**
     * 获取指定时间的时间戳
     */
    public static long getMillisecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 比较两个时间大小
     */
    public static boolean compare(Date first, Date second) {
        return first.before(second);
    }

    /**
     * 比较两个时间大小
     */
    public static boolean compareLocalDate(LocalDate first, LocalDate second) {
        return first.isEqual(second);
    }



    public static boolean validateDayDiff(Date from, Date to, int topLimit) {
        int num = DateUtils.diffDay(from, to);
        return num > topLimit;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isSameHour(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY);
    }

    public static Date now() {
        return new Date();
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        //设置为指定日期
        calendar.setTime(date);
        //指定日期月份减去一后的 第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //获取最终的时间
        return calendar.getTime();
    }

    public static Date getFirstDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        //设置为指定日期
        calendar.setTime(date);
        //指定日期月份减去一
        calendar.add(Calendar.MONTH, -1);
        //指定日期月份减去一后的 第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //获取最终的时间
        return calendar.getTime();
    }

    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        //设置为指定日期
        calendar.setTime(date);
        //指定日期月份减去一
        calendar.add(Calendar.MONTH, -1);
        //指定日期月份减去一后的 最大天数
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //获取最终的时间
        return calendar.getTime();
    }


    /**
     * 获取当天的开始时间
     */
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    /**
     * 获取前天的开始时间
     */
    public static Date getBeforeDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -2);
        return cal.getTime();
    }



    /**
     * 判断校验日期是否过期
     */
    public static boolean checkIsExpired(Date startDate, Date endDate, Date checkDate) {
        return checkDate.compareTo(startDate) < 0 || checkDate.compareTo(endDate) > 0;
    }

    /**
     * 判断校验日期是否过期
     */
    public static Date dateTo0BeginOr23End(Date date, Boolean flag) {
        Calendar calendar = Calendar.getInstance();
        Date resultDate;
        //获取某一天的0点0分0秒 或者 23点59分59秒
        if (flag) {
            calendar.setTime(date);
            calendar
                .set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    0, 0, 0);
            resultDate = calendar.getTime();
        } else {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date);
            calendar
                .set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
                    23, 59, 59);
            resultDate = calendar.getTime();
        }
        return resultDate;
    }

    /**
     * 获取当前月份的第一天开始时间
     */
    public static Date getFirstDayTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取当前时间点当前零时加减月份的时间
     */
    public static Date getCurrentAppointDay(int index) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDayBegin());
        calendar.add(Calendar.MONTH, index);
        return calendar.getTime();
    }

    /**
     * 获取当前时间点当前零时加减天数的时间
     */
    public static Date get2CurrentAppointDay(int index) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDayBegin());
        calendar.add(Calendar.DAY_OF_MONTH, index);
        return calendar.getTime();
    }




    /**
     * 获取格式YYYY-mm-dd 格式的日期起至时间和结束时间
     */
    public static List<String> getYearMonthDayList(Date date, int diffDay) {
        List<String> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar
            .set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0,
                0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while (diffDay > 0) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            Date endTime = calendar.getTime();
            dateList.add(simpleDateFormat.format(endTime));
            --diffDay;
        }
        return dateList;
    }

    public static String getYearMonthDayFormmatString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getYearMonthDayFormmatString(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = null;
        try {
            newDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return newSimpleDateFormat.format(newDate);
    }


    public static Date getYearMonthDayFormmatDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar.getTime();
    }

    public static int dateCompare(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateFirst = dateFormat.format(date1);
        String dateLast = dateFormat.format(date2);
        int dateFirstIntVal = Integer.parseInt(dateFirst);
        int dateLastIntVal = Integer.parseInt(dateLast);
        if (dateFirstIntVal > dateLastIntVal) {
            return 1;
        } else if (dateFirstIntVal < dateLastIntVal) {
            return -1;
        }
        return 0;
    }

    public static Date convertLocalDateToDate(LocalDateTime now) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = now.atZone(zoneId);

        return Date.from(zdt.toInstant());
    }

    public static Date set(Date date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    /**
     * 获取格式YYYY-mm-dd 格式的日期起至时间和结束时间
     */
    public static String getLastDayYearMonthDayFormmatTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar
            .set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0,
                0);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date endTime = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(endTime);
    }

    public static Date currentDay() {
        LocalDate localDate = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);


        return date;
    }

    public static Date getMaxTime(Date firstTime, Date secondTime) {
        if (firstTime == null) {
            return secondTime;
        }
        if (secondTime == null) {
            return firstTime;
        }
        if (compare(firstTime, secondTime)) {
            return secondTime;
        } else {
            return firstTime;
        }
    }

    /**
     * 返回第二天的开始
     */
    public static Date startSecDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH+1),
            0, 0, 0);
        return calendar.getTime();
    }
    public static void main(String[] args) {
        Date d = new Date(1623454200000l);

        System.out.println(startSecDay(new Date()).getTime()/1000);
        System.out.println(format(startSecDay(new Date()),STR_DATE_FORMAT));

        LocalDateTime localDateTime =LocalDateTime.now();
        System.out.println(localDateTime.getDayOfWeek().getValue());
        System.out.println(localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

    }

}
