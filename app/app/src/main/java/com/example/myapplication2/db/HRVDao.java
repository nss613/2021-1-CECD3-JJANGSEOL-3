package com.example.myapplication2.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HRVDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE
    )
    void insert(Result result);

    @Delete
    void delete(Result result);

    @Query("SELECT * FROM Result")
    List<Result> getAllResult();

    @Query("SELECT * FROM Result WHERE date BETWEEN :num AND :num+40")
    List<Result> getResult(int num);

}
