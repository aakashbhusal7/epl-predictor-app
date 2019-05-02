package com.example.eplpredictor.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.eplpredictor.data.entity.User;

import java.util.List;

/**
 * Created by aakash on 30,April,2019
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void listUsers(List<User> userList);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM user_table where userId = :id")
    User getUserById(String id);

    @Query("SELECT COUNT(*) from user_table")
    int getCountOfUsers();

}
