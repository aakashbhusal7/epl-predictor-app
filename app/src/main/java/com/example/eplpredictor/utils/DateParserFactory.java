package com.example.eplpredictor.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by aakash on 29,April,2019
 */
public class DateParserFactory {
    private static String TAG = DateParserFactory.class.getSimpleName();
    private static String finalDate, flagMessage;
    private static Date todayDate, finalApiDate;
    private static Date passedApiDate, passedTodayDate;

    public static String parseDate(String passedDate) {
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = utcFormat.parse(passedDate);

            DateFormat defaultFormat = new SimpleDateFormat("HH:mm");
            defaultFormat.setTimeZone(TimeZone.getDefault());
            finalDate = defaultFormat.format(date);
            System.out.println(defaultFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "on parse error");
        }
        return finalDate;
    }

    public static String getLocalDate() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return simpleDateFormat.format(new Date());
    }

    public static Date getTodayDate(){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            todayDate= simpleDateFormat.parse(getLocalDate());

        }
        catch (ParseException exception){
            exception.printStackTrace();
            Log.d(TAG, "parse error");
        }
        return todayDate;
    }

    public static Date getUtcDate(String apiDate) {
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            finalApiDate=utcFormat.parse(apiDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return finalApiDate;
    }


    public static String compareDate(Date passedApiDate, Date passedTodayDate){
        if(passedTodayDate.after(passedApiDate)) {
            flagMessage = Constants.DATE_AFTER_FLAG;
        }
        else{
            flagMessage=Constants.DATE_BEFORE_FLAG;
        }
        return flagMessage;
    }
}
