package com.example.eplpredictor.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by aakash on 30,April,2019
 */

@Entity(tableName = "match_table")
public class Match {
    @PrimaryKey
    @NonNull
    private String matchId;
    private String homeTeamId;
    private String scoreId;
    private String awayTeamId;

    @Ignore
    public Match(){}

    public Match(@NonNull String matchId, String homeTeamId, String scoreId, String awayTeamId) {
        this.matchId = matchId;
        this.homeTeamId = homeTeamId;
        this.scoreId=scoreId;
        this.awayTeamId = awayTeamId;
    }

    @NonNull
    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(@NonNull String matchId) {
        this.matchId = matchId;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
}
