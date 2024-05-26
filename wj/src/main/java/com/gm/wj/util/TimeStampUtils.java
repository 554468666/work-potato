package com.gm.wj.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeStampUtils {

    /**
     * 毫秒级别时间戳
     *
     * @return 返回时间戳为年月日时分秒转换成的毫秒数
     */
    public String MillionthTimeStamp() {
        // 获取当前时间的Instant对象
        Instant now = Instant.now();

        // 如果需要将Instant转换为特定时区（如系统默认时区）的LocalDateTime
        // 注意：LocalDateTime没有时区信息，但是我们可以将其与ZoneId结合来转换
        LocalDateTime localDateTime = now.atZone(ZoneId.systemDefault()).toLocalDateTime();
        // 将Instant转换为时间戳（毫秒）
        String timestamp = String.valueOf(now.toEpochMilli());
        // 打印时间戳
        System.out.println("Current Timestamp (milliseconds): " + timestamp);
        return timestamp;
    }

    /**
     * 时间格式时间戳
     * @return 202405252011
     */
    public String DateTimeStamp() {
        // 获取当前时间的Instant对象
        Instant now = Instant.now();

        // 如果需要将Instant转换为特定时区（如系统默认时区）的LocalDateTime
        // 注意：LocalDateTime没有时区信息，但是我们可以将其与ZoneId结合来转换
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());

        // 设置格式为 yyyyMMddHHmmss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = zonedDateTime.format(formatter);

        return formattedDateTime;
    }
}