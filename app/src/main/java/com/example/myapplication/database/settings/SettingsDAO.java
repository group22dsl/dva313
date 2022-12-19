package com.example.myapplication.database.settings;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SettingsDAO {

    @Insert
    void addSettings(SettingsEntry appSettings);

    @Query("SELECT * FROM Settings")
    List<SettingsEntry> getAllSettings();

    @Query("SELECT * FROM Settings WHERE id = :ID LIMIT 1")
    SettingsEntry getSettings (int ID);

    @Delete
    void deleteAPPSettings(SettingsEntry toDelete);

    @Update
    void updateAPPSettings(SettingsEntry appSettings);

    @Query("DELETE FROM Settings")
    void resetTable();
}
