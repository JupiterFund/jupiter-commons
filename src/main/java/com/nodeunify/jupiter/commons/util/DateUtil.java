package com.nodeunify.jupiter.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static int getCurrentDate() {
        Date date = new Date();
        int currentDate = Integer.parseInt(sdf.format(date));
        return currentDate;
    }
}
