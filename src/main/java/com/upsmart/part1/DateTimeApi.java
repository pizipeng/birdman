package com.upsmart.part1;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pxh on 2017/10/25.
 * java 8提供了全新的时间日期API,全放在java.time包下，新的时间日期是API是基于
 * JodaTime库开发的。但是也不尽相同。
 */
public class DateTimeApi {

    /**
     * Clock提供了对当前日期和时间的访问功能。Clock是对当前时区敏感的，
     * 并可用于替代System.currentTimeMillis()方法来获取当前的毫秒时间
     * 当前时间线上的时刻可以用Instance类来表示。Instance也能用来创建原先的
     * java.util.Date对象
     */
    @Test
    public void clockTest() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();

        Instant instant = clock.instant();
        Date date = Date.from(instant);
        System.out.println(millis);
        System.out.println(date);
    }

    /**
     * 时区类可以用一个ZoneId来表示。时区类的对象可以通过静态工厂方法方便的获取。时区类还定义
     * 了一个偏移量，用来在当前时刻或某时间与目标时区时间之间进行切换
     */
    @Test
    public void timezonesTest() {
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }

    /**
     * 本地时间类表示一个没有指定时区的时间，
     * LocalTime 是由多个工厂方法组成，其目的是为了简化对时间对象的创建和操作，
     * 包括对字符串解析的操作。
     */
    @Test
    public void localTimeTest() {
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);
        System.out.println(minutesBetween);

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37

    }

    /**
     * 本地时间表示了一个独一无二的时间，这个时间是不可变的，与LocalTime同源的，
     * 下面的每一步操作都返回一个新对象。
     */
    @Test
    public void localDateTest() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println(yesterday);

        LocalDate independencyDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independencyDay.getDayOfWeek();
        System.out.println(dayOfWeek);

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   // 2014-12-24

    }

    /**
     * LocalDateTime 表示日期-时间。它将刚才介绍的日期对象和时间对象结合了起来，
     * 形成了一个对象实例，LocalDateTime是不可变的
     */
    @Test
    public void localDateTimeTest() {
        LocalDateTime sylvester =
                LocalDateTime.of(2014, Month.JULY, 31, 23, 59, 59);
        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);

        Month month = sylvester.getMonth();
        System.out.println(month);

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);

    }



}
