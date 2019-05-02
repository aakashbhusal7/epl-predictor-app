package com.example.eplpredictor.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.eplpredictor.utils.Constants;

/**
 * Created by aakash on 02,May,2019
 */

@Entity(tableName = Constants.FULLTIME_TABLE_NAME,
        foreignKeys = @ForeignKey(entity = Score.class,
                                    parentColumns = "id",
                                    childColumns = "fulltime_id",
                                    onDelete = ForeignKey.NO_ACTION))
public class FullTime {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "hometeam_score")
    private String homeTeamScore;

    @ColumnInfo(name = "awayteam_score")
    private String awayTeamScore;

    @ColumnInfo(name = "fulltime_id")
    private String fullTimeId;
}
