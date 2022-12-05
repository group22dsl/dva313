package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "APP_FILTERS")
public class APPFilters {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "app_name")
    private String APP_NAME;

    @ColumnInfo(name = "category_one")
    private boolean CATEGORY_ONE;

    @ColumnInfo(name = "category_two")
    private boolean CATEGORY_TWO;

    @Ignore
    public APPFilters(String APP_NAME, boolean CATEGORY_ONE, boolean CATEGORY_TWO) {
        this.APP_NAME = APP_NAME;
        this.CATEGORY_ONE = CATEGORY_ONE;
        this.CATEGORY_TWO = CATEGORY_TWO;
    }


    public APPFilters(int id, String APP_NAME, boolean CATEGORY_ONE, boolean CATEGORY_TWO) {
        this.id = id;
        this.APP_NAME = APP_NAME;
        this.CATEGORY_ONE = CATEGORY_ONE;
        this.CATEGORY_TWO = CATEGORY_TWO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAPP_NAME() {
        return APP_NAME;
    }

    public void setAPP_NAME(String APP_NAME) {
        this.APP_NAME = APP_NAME;
    }

    public boolean isCATEGORY_ONE() {
        return CATEGORY_ONE;
    }

    public void setCATEGORY_ONE(boolean CATEGORY_ONE) {
        this.CATEGORY_ONE = CATEGORY_ONE;
    }

    public boolean isCATEGORY_TWO() {
        return CATEGORY_TWO;
    }

    public void setCATEGORY_TWO(boolean CATEGORY_TWO) {
        this.CATEGORY_TWO = CATEGORY_TWO;
    }
}
