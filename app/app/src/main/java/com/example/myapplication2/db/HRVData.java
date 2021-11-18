package com.example.myapplication2.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HRVData {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    public float MEAN_RR;

    @ColumnInfo
    public float MEDIAN_RR;

    @ColumnInfo
    public float SDRR;

    @ColumnInfo
    public float RMSSD;

    @ColumnInfo
    public float SDSD;

    @ColumnInfo
    public float SDRR_RMSSD;

    @ColumnInfo
    public float HR;

    @ColumnInfo
    public float pNN25;

    @ColumnInfo
    public float pNN50;

    @ColumnInfo
    public float SD1;

    @ColumnInfo
    public float SD2;

    @ColumnInfo
    public float KURT;

    @ColumnInfo
    public float SKEW;

    @ColumnInfo
    public float MEAN_REL_RR;

    @ColumnInfo
    public float MEDIAN_REL_RR;

    @ColumnInfo
    public float SDRR_REL_RR;

    @ColumnInfo
    public float RMSSD_REL_RR;

    @ColumnInfo
    public float SDSD_REL_RR;

    @ColumnInfo
    public float SDRR_RMSSD_REL_RR;

    @ColumnInfo
    public float KURT_REL_RR;

    @ColumnInfo
    public float SKEW_REL_RR;

    @ColumnInfo
    public float VLF;

    @ColumnInfo
    public float VLF_PCT;

    @ColumnInfo
    public float LF;

    @ColumnInfo
    public float LF_PCT;

    @ColumnInfo
    public float LF_NU;

    @ColumnInfo
    public float HF;

    @ColumnInfo
    public float HF_PCT;

    @ColumnInfo
    public float HF_NU;

    @ColumnInfo
    public float TP;

    @ColumnInfo
    public float LF_HF;

    @ColumnInfo
    public float HF_LF;

    @ColumnInfo
    public float sampen;

    @ColumnInfo
    public float higuci;

    @ColumnInfo
    public float datasetId;

    @ColumnInfo
    public String condition;

    @ColumnInfo
    public String date;

}
