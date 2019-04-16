package com.example.eplpredictor.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aakash on 16,April,2019
 */
public class Matches {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("utcDate")
    @Expose
    private String utcDate;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("matchday")
    @Expose
    private int matchDay;

    @SerializedName("score")
    @Expose
    private Score score;

    @SerializedName("homeTeam")
    @Expose
    private HomeTeam homeTeam;

    @SerializedName("awayTeam")
    @Expose
    private AwayTeam awayTeam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(int matchDay) {
        this.matchDay = matchDay;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }
}
