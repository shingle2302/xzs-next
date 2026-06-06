package com.mindskip.xzs.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
    public static final String STANDER_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String dateFormat(Date date) {
        return dateFormat(date, STANDER_FORMAT);
    }

    public static String dateFormat(Date date, String format) {
        if (date == null) return "";
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static Date parse(String dateStr, String format) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(format));
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getMonthStartDay() {
        LocalDate now = LocalDate.now();
        LocalDate monthStart = now.withDayOfMonth(1);
        return Date.from(monthStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date getMonthEndDay() {
        LocalDate now = LocalDate.now();
        LocalDate monthEnd = now.withDayOfMonth(now.lengthOfMonth());
        return Date.from(monthEnd.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
    }
}
