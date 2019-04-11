package com.example.eplpredictor.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aakash on 11,April,2019
 */
public class SharedPreferencesManager {
    SharedPreferences sharedPreferences;
    private Context mContext;
    private static final String PREF_NAME="LOGIN_PREF";
    private SharedPreferences.Editor editor;

    public SharedPreferencesManager(Context context){
        mContext=context;
        sharedPreferences=mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void saveLogin(Context context, Boolean isLoggedIn){
        mContext=context;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("IS_LOGGED_IN",isLoggedIn);
        editor.apply();
    }
    public boolean loadLogin(){
        sharedPreferences=mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("IS_LOGGED_IN",false);
    }

    public void saveToken(Context context, String token){
        mContext=context;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Constants.userToken,token);
        editor.apply();
    }

    public String loadToken(){
        sharedPreferences=mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.userToken,null);
    }

    public void saveName(Context context, String name){
        mContext=context;
        sharedPreferences=mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Constants.userName,name);
        editor.apply();
    }

    public String loadName(){
        sharedPreferences=mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.userName,null);
    }

    public void saveEmail(Context context, String email){
        mContext=context;
        sharedPreferences=mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Constants.userEmail,email);
        editor.apply();
    }

    public String loadEmail(){
        sharedPreferences=mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.userEmail,null);
    }

    public void savePhoto(Context context, String photo){
        mContext=context;
        sharedPreferences=mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(Constants.userPhoto,photo);
        editor.apply();
    }

    public String loadPhoto(){
        sharedPreferences=mContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.userPhoto,null);
    }

    public void clear(){
        editor.clear();
        editor.apply();
    }
}
