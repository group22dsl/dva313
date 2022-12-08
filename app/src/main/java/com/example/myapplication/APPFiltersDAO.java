package com.example.myapplication;

import androidx.annotation.WorkerThread;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface APPFiltersDAO {

    @Insert
    void addAppFilter(APPFilters appFilters);

    @Query("SELECT * FROM APP_FILTERS")
    List<APPFilters> getAllFilters();

    @Query("SELECT * FROM APP_FILTERS WHERE id = :app_filter_id LIMIT 1")
    APPFilters getAPPFilter (int app_filter_id);

    @Delete
    void deleteAPPFilter(APPFilters toDelete);

    @Update
    void updateAPPFilter(APPFilters appFilters);

    @WorkerThread
    abstract void clearAllTables();
}
