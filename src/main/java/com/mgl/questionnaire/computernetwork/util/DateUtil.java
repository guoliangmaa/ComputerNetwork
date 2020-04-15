package com.mgl.questionnaire.computernetwork.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getStringDate(Long date){
        Date date1 = new Date(date);
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date1);
    }
}
