package com.example.eplpredictor.utils;

import org.json.JSONException;

import java.io.IOException;

import retrofit2.adapter.rxjava2.HttpException;

/**
 * Created by aakash on 16,April,2019
 */
public class ErrorMessageFactory {
    private static String errorMessage;
    public static String createMessage(Throwable throwable){
        if(throwable instanceof JSONException){
            errorMessage="Error while parsing result";
        }
        else if(throwable instanceof IOException){
            errorMessage="Network error";
        }
        else if(throwable instanceof HttpException){
            errorMessage="Failed to load response";
        }
        else if(throwable instanceof retrofit2.HttpException){
            errorMessage="Retrofit error";
        }
        else{
            errorMessage="Unknown error";
        }
        return errorMessage;
    }
}
