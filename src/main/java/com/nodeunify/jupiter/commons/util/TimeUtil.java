package com.nodeunify.jupiter.commons.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class TimeUtil {
    private static final DateTimeFormatter parser = DateTimeFormatter.ofPattern("H:mm:ss.SSS");
    private static final DateTimeFormatter formater = DateTimeFormatter.ofPattern("HHmmssSSS");

    public static int buildTime(String time, int millisec) {
        LocalTime parsed = LocalTime.parse(time + "." + String.format("%03d", millisec), parser);
        String formatted = parsed.format(formater);
        int localTime = Integer.valueOf(formatted);
        return localTime;
    }

    /**
     * Extract time part from integer value of datetime string
     * E.g.
     * 20191220223145320 -> 22314530
     * @param datetime
     * @return
     */
    public static int extractTime(int datetime) {
        int time = datetime % 1000000000;
        return time;
    }

    public static int extractTime(long datetime) {
        int time = Math.toIntExact(datetime % 1000000000);
        return time;
    }
}
