package com.example.myapplication.database.whitelist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WHITELIST")
public class WhitelistEntry {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String ID;

    public WhitelistEntry(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        this.ID = id;
    }
}
