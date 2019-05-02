package com.example.eplpredictor.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.eplpredictor.utils.Constants;

/**
 * Created by aakash on 30,April,2019
 */

@Entity(tableName = Constants.MATCHES_TABLE_NAME)
public class Match {
    @PrimaryKey
    @NonNull
    private String id;

    private String utcDate;
    private String status;
    private int matchday;
    private String seasonId;
    private String scoreId;
    private String homeTeamId;
    private String awayTeamId;


    @Ignore
    public Match(){}


    public Match(@NonNull String id, String utcDate, String status, int matchday, String seasonId, String scoreId, String homeTeamId, String awayTeamId) {
        this.id = id;
        this.utcDate = utcDate;
        this.status = status;
        this.matchday = matchday;
        this.seasonId = seasonId;
        this.scoreId = scoreId;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
}
