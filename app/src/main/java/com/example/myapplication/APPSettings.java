package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Settings")
public class APPSettings {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "settings_name")
    private String name;


    @Ignore
    public APPSettings(String name) {
        this.name = name;
    }

    public APPSettings(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
