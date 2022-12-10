package com.example.ee193take2.ui.database;

import androidx.room.TypeConverter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.logging.SimpleFormatter;


public class Converters {
    @TypeConverter
    public static Date toDate(String a) {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(a);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            return a == null ? null : sqlDate;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @TypeConverter
    public static String fromDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return date == null ? null : strDate;
    }
}
