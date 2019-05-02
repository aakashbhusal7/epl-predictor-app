package com.example.eplpredictor.data;

import com.example.eplpredictor.data.dao.MatchDao;
import com.example.eplpredictor.data.dao.UserDao;
import com.example.eplpredictor.model.remote.Fixtures;

import io.reactivex.Observable;

/**
 * Created by aakash on 02,May,2019
 */
public class LocalRepoImpl implements LocalRepo {
    private MatchDao matchDao;
    private UserDao userDao;

    public LocalRepoImpl(MatchDao matchDao){
        this.matchDao=matchDao;
    }

    @Override
    public Observable<Fixtures> getFixtures() {
        return null;
    }
}
