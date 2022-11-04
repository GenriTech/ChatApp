package com.genriking.chatapp.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {
    public static String convertTimestampToTime(Long timestamp){
        if (timestamp == null) {
            return "";
        }
        Date date = new Date(timestamp);
        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());

        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }
}
