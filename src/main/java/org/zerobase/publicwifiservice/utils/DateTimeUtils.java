package org.zerobase.publicwifiservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    /**
     * 년-월-일 시:분:초
     */
    public static String format(final LocalDateTime localDateTime) {
        DateTimeFormatter YYYY_MM_DD_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(YYYY_MM_DD_HH_mm_ss);
    }
}
