package com.example.eplpredictor.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.eplpredictor.data.entity.Match;

import java.util.List;

/**
 * Created by aakash on 30,April,2019
 */

@Dao
public interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMatches(Match match);

    @Query("SELECT COUNT(*) FROM match_table")
    int getCountMatches();

    @Query("SELECT * FROM match_table")
    List<Match> getAllMatches();


}
