package com.example.eplpredictor.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.eplpredictor.utils.Constants;

/**
 * Created by aakash on 30,April,2019
 */

@Entity(tableName = Constants.SCORE_TABLE_NAME,
            foreignKeys = @ForeignKey(entity = Match.class,
                                       parentColumns = "id",
                                        childColumns = "match_id",
                                        onDelete = ForeignKey.NO_ACTION))
public class Score {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "fulltime_id")
    private String fullTimeId;

    @ColumnInfo(name = "match_id")
    private String matchId;

}
