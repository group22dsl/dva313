package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SettingsDAO {

    @Insert
    void addSettings(APPSettings appSettings);

    @Query("SELECT * FROM Settings")
    List<APPSettings> getAllSettings();

    @Query("SELECT * FROM Settings WHERE id = :settings_id LIMIT 1")
    APPSettings getSettings (int settings_id);

    @Delete
    void deleteAPPSettings(APPSettings toDelete);

    @Update
    void updateAPPSettings(APPSettings appSettings);

    @Query("DELETE FROM Settings")
    void resetTable();
}
