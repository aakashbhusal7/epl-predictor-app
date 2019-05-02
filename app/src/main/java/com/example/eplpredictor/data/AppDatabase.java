package com.example.eplpredictor.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.eplpredictor.data.dao.MatchDao;
import com.example.eplpredictor.data.dao.UserDao;
import com.example.eplpredictor.data.entity.Match;
import com.example.eplpredictor.data.entity.User;

/**
 * Created by aakash on 02,May,2019
 */

@Database(entities = {User.class, Match.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract MatchDao matchDao();
}
