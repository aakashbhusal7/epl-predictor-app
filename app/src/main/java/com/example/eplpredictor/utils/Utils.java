package com.example.eplpredictor.utils;

import android.content.Context;

/**
 * Created by aakash on 11,April,2019
 */
public class Utils {

    private Context mContext = null;

    /**
     * Public constructor that takes mContext for later use
     */
    public Utils(Context con) {
        mContext = con;
    }

    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

}
