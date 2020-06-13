package com.webbleen.webblog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-13 10:54
 * @description：
 */

public class DateUtils {

    public static String getTime4yyyyMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        synchronized(sdf) {
            return sdf.format(date);
        }
    }
}
