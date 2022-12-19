package com.example.myapplication.database.cache;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CACHE")
public class CacheEntry {

    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name = "id")
    private String ID;

    @ColumnInfo(name = "priority")
    private String PRIORITY;

    @ColumnInfo(name = "data")
    private String DATA;


    public CacheEntry(String ID, String PRIORITY, String DATA) {
        this.ID = ID;
        this.PRIORITY = PRIORITY;
        this.DATA = DATA;
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public String getPRIORITY() {
        return PRIORITY;
    }

    public void setPRIORITY(String PRIORITY) {
        this.PRIORITY = PRIORITY;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
