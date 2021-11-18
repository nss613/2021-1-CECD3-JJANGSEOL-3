package com.example.myapplication2.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Result {

    @PrimaryKey(autoGenerate = false)
    public int date;

    @ColumnInfo
    public String percent;

}
