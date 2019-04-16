package com.example.eplpredictor.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aakash on 16,April,2019
 */
public class FullTime {

    @SerializedName("homeTeam")
    @Expose
    private int homeTeamScore;

    @SerializedName("awayTeam")
    @Expose
    private int awayTeamScore;

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
}
