package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WhitelistDAO {

    @Insert
    void addWhitelistEntry(whitelistEntry whitelist);

    @Query("SELECT * FROM WHITELIST")
    List<whitelistEntry> getEntireWhitelist();

    @Query("SELECT * FROM WHITELIST WHERE id = :ID LIMIT 1")
    whitelistEntry getWhitelistEntry(String ID);

    @Delete
    void deleteWhitelist(whitelistEntry toDelete);

    @Update
    void updateWhitelist(whitelistEntry toUpdate);
}
