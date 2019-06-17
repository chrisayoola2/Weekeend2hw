package com.example.weekeend2hw;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CelebrityDao {

    @Insert
    void insert(Celebrity celebrity);

    @Update
    void update(Celebrity celebrity);

    @Delete
    void delete(Celebrity celebrity);

    @Query("DELETE FROM celebrity_table")
    void deleteAllCelebrity();

    @Query("SELECT * FROM celebrity_table ORDER BY celebrityName ASC")
    LiveData<List<Celebrity>> getAllCelebrity();


}
