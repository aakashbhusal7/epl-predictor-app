package com.example.eplpredictor.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aakash on 16,April,2019
 */
public class HomeTeam {

    @SerializedName("id")
    @Expose
    private int homeId;

    @SerializedName("homeTeam")
    @Expose
    private String homeTeamName;

    public int getHomeId() {
        return homeId;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }
}
