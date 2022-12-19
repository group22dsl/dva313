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

    @ColumnInfo(name = "send_freq")
    private int SEND_FREQ;


    public WhitelistEntry(String ID, int SEND_FREQ) {
        this.ID = ID;
        this.SEND_FREQ = SEND_FREQ;
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public int getSEND_FREQ() {
        return SEND_FREQ;
    }

    public void setSEND_FREQ(int SEND_FREQ) {
        this.SEND_FREQ = SEND_FREQ;
    }
}
