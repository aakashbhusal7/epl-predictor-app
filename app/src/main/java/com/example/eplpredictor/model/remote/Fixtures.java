package com.example.eplpredictor.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aakash on 16,April,2019
 */
public class Fixtures {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("filters")
    @Expose
    private Filters filters;

    @SerializedName("matches")
    @Expose
    private List<Matches>matches=null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public List<Matches> getMatches() {
        return matches;
    }

    public void setMatches(List<Matches> matches) {
        this.matches = matches;
    }
}
