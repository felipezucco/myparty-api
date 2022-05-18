package com.myparty.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.myparty.enums.DateFormatEnum;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateFormat {

    public static Date format(DateFormatEnum format, String date) {
        try {
            return new SimpleDateFormat(format.getFormat()).parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateFormat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String format(DateFormatEnum format, Date date) {
        return new SimpleDateFormat(format.getFormat()).format(date);
    }
}
