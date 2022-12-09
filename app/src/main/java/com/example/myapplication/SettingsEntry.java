package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Settings")
public class SettingsEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String NAME;

    @ColumnInfo(name = "value")
    private String VALUE;

    public SettingsEntry(int id, String NAME, String VALUE) {
        this.id = id;
        this.NAME = NAME;
        this.VALUE = VALUE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String name) {
        this.NAME = name;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String value) {
        this.VALUE = value;
    }
}
