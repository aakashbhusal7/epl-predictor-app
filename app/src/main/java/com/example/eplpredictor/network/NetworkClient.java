package com.example.eplpredictor.network;

import com.example.eplpredictor.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aakash on 16,April,2019
 */
public class NetworkClient {

    private static Retrofit retrofit;

    public NetworkClient(){}

    public static Retrofit getRetrofit(){
        if(retrofit==null){

            OkHttpClient okhttpClient=new OkHttpClient.Builder()
                    .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.MINUTES)
                    .readTimeout(Constants.READ_TIMEOUT,TimeUnit.SECONDS)
                    .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            retrofit=new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttpClient)
                    .build();
        }

        return retrofit;
    }
}
