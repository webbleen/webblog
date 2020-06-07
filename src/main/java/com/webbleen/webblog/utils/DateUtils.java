package com.webbleen.webblog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getTime4yyyyMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        synchronized(sdf) {
            return sdf.format(date);
        }
    }
}
