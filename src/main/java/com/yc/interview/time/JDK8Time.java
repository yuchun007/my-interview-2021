package com.yc.interview.time;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.OptionalDouble;

/**
 * jdk1.1时建议使用Calendar替换Date
 * calendar缺点：
 *     1.calendar可变
 *     2.偏移量问题
 *     3.格式化问题，格式化只对Date有用，Calendar则不行
 *    此外Date，Calendar是线程不安全的，不能处理闰秒等
 *
 * 所以jdk8吸收Joda-Time（可以作为jar包引用）的精华，推出新的时间api java.time 包含：
 *     本地日期、本地时间、本地日期时间、时区和持续时间（Duration）的类
 *
 *    Instant 瞬时
 *
 *    DateTimeFormatter:格式化或解析日期或时间 ,类似于SimpleDateFormat
 *       //方式一：预定义的标准格式，如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
 *         //方式二：本地化的相关格式, 如：ofLocalizedDate（FormatStyle.LONG）
 *         //方式三：自定义的相关格式, 如：ofPattern("yyyy-MM-dd hh:mm:ss E")
 */
public class JDK8Time {
    @Test
    public void testDate(){
        Date date = new Date(2021, 4, 29);
        System.out.println(date);
        //结果： Sun May 29 00:00:00 CST 3921  存在偏移
        Date date1 = new Date(2021-1900, 4-1, 29);
        //结果：Thu Apr 29 00:00:00 CST 2021
        System.out.println(date1);
    }

    @Test
    public void testLocalDate(){
        //now()获取当前的日期、时间、日期时间,实例化
        LocalDate now = LocalDate.now();
        LocalTime now1 = LocalTime.now();
        LocalDateTime now2 = LocalDateTime.now();

        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);

        //of(): 可以指定日期时间，没有偏移量
        LocalDateTime of = LocalDateTime.of(2021, 4, 29, 16, 25, 30);
        System.out.println(of);

        //getXxx()
        System.out.println(of.getDayOfMonth());
        System.out.println(of.getDayOfWeek());
        System.out.println(of.getMonth());
        System.out.println(of.getMonthValue());
        System.out.println(of.getMinute());

        //withXxx()设置相关属性 体现不可变性
        LocalDateTime ofNew = of.withDayOfMonth(30);
        System.out.println(of);
        System.out.println(ofNew);
    }

    @Test
    public void testInstant(){
        //获取Instant实例,获取本初子午线对应的标准时间
        Instant instant = Instant.now();
        System.out.println(instant);

        //添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //获取瞬时点的毫秒数
        long l = instant.toEpochMilli();
        System.out.println(l);

        //根据毫秒数，获取instant实例
        Instant instant1 = Instant.ofEpochMilli(l);
        System.out.println(instant1);

    }

    @Test
    public void testDateTimeFormatter(){
        //方式一：预定义的标准格式（较少使用），如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化：日期->字符串
        LocalDateTime now = LocalDateTime.now();
        String str1 = isoLocalDateTime.format(now);
        //解析：字符串-> 日期
        TemporalAccessor parse = isoLocalDateTime.parse(str1);

        //方式二：本地化的相关格式（较少使用）, 如：ofLocalizedDate（FormatStyle.LONG）
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String format = dateTimeFormatter.format(now);
        System.out.println(format);

        //方式三：自定义的相关格式(一般用这个), 如：ofPattern("yyyy-MM-dd hh:mm:ss E")
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String format1 = dateTimeFormatter1.format(now);
        System.out.println(format1);

    }
}
