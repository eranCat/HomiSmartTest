package com.erank.homismarttest.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.erank.homismarttest.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

//    @Query("SELECT * FROM user WHERE id IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);

//    @Query("SELECT * FROM user WHERE name=:name LIMIT 1")
//    User findByName(String name);

    @Insert
    void insertAll(User... users);

//    @Delete
//    void delete(User user);
}