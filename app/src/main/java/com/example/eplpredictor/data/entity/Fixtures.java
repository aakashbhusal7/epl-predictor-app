package com.example.eplpredictor.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.eplpredictor.utils.Constants;

/**
 * Created by aakash on 02,May,2019
 */

@Entity(tableName = Constants.FIXTURES_TABLE_NAME)
public class Fixtures {

    @PrimaryKey
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
