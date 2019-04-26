package com.example.eplpredictor.network;

import com.example.eplpredictor.BuildConfig;
import com.example.eplpredictor.model.remote.Fixtures;
import com.example.eplpredictor.model.remote.Matches;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by aakash on 16,April,2019
 */
public interface RestApi {

    @GET("competitions/2021/matches")
    Observable<Fixtures>getFixtures(@Header("X-Auth-Token")String token,
                                    @Query("matchday") String matchday
                                    );

}
