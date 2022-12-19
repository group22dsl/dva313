package com.example.myapplication.database.whitelist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WhitelistDAO {

    @Insert
    void addWhitelistEntry(WhitelistEntry whitelist);

    @Query("SELECT * FROM WHITELIST")
    List<WhitelistEntry> getEntireWhitelist();

    @Query("SELECT * FROM WHITELIST WHERE id = :ID LIMIT 1")
    WhitelistEntry getWhitelistEntry(String ID);

    @Delete
    void deleteWhitelist(WhitelistEntry toDelete);

    @Update
    void updateWhitelist(WhitelistEntry toUpdate);

    @Query("DELETE FROM WHITELIST")
    void resetTable();

}
