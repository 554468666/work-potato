package com.gm.wj.test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimestampExample {
    public static void main(String[] args) {
        // 获取当前时间的Instant对象  
        Instant now = Instant.now();

        // 如果需要将Instant转换为特定时区（如系统默认时区）的LocalDateTime  
        // 注意：LocalDateTime没有时区信息，但是我们可以将其与ZoneId结合来转换  
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = zonedDateTime.format(formatter);
        // 打印LocalDateTime，仅用于验证（不是时间戳）  
        System.out.println("Current LocalDateTime: " + formattedDateTime);

    }
}