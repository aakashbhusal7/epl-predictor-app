package com.example.eplpredictor.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by aakash on 30,April,2019
 */

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    @NonNull
    private String userId;
    private int userImage;
    private String userName;

    @Ignore
    public User(){}

    public User(@NonNull String userId, int userImage, String userName) {
        this.userId = userId;
        this.userImage = userImage;
        this.userName = userName;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
