package com.erank.homismarttest.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.erank.homismarttest.models.User;

import java.util.List;

@Dao
public interface RecordDao {
    @Query("SELECT * FROM record")
    List<User.Record> getAll();

    @Query("SELECT * FROM record WHERE uid = :uid")
    LiveData<List<User.Record>> getAllByUserIdSync(String uid);

    @Query("SELECT * FROM record WHERE uid = :uid")
    List<User.Record> getAllByUserId(String uid);

    @Insert
    void insertAll(User.Record... records);

    @Delete
    void delete(User.Record record);
}