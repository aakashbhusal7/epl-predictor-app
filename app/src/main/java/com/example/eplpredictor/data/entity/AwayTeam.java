package com.example.eplpredictor.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by aakash on 30,April,2019
 */

@Entity(tableName = "away_team_table")
public class AwayTeam {

    @PrimaryKey
    @NonNull
    private String id;

    private String name;

    @Ignore
    public AwayTeam(){}

    public AwayTeam(@NonNull String id, String name) {
        this.id = id;
        this.name = name;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
