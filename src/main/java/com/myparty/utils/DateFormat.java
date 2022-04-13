package com.myparty.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.myparty.enums.DateFormatEnum;

public class DateFormat {

    public static Date format(DateFormatEnum format, String date) throws ParseException {
        return new SimpleDateFormat(format.getFormat()).parse(date);
    }
    
    public static String format(DateFormatEnum format, Date date) {
        return new SimpleDateFormat(format.getFormat()).format(date);
    }
}
