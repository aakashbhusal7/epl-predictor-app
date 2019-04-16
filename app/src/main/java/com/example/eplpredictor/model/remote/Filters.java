package com.example.eplpredictor.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aakash on 16,April,2019
 */
public class Filters {

    @SerializedName("matchday")
    @Expose
    private String matchday;

    public String getMatchday() {
        return matchday;
    }

    public void setMatchday(String matchday) {
        this.matchday = matchday;
    }



}
