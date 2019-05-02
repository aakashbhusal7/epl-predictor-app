package com.example.eplpredictor.data;

import com.example.eplpredictor.model.remote.Fixtures;

import io.reactivex.Observable;

/**
 * Created by aakash on 02,May,2019
 */
public interface LocalRepo {
    Observable<Fixtures>getFixtures();
}
