package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface APPFiltersDAO {

    @Insert
    public void addAppFilter(APPFilters appFilters);

    @Query("SELECT * FROM APP_FILTERS")
    public List<APPFilters> getAllFilters();

    @Query("SELECT * FROM APP_FILTERS WHERE id = :app_filter_id LIMIT 1")
    public APPFilters getAPPFilter (int app_filter_id);

    @Delete
    public void deleteAPPFilter(APPFilters appFilters);

    @Update
    public void updateAPPFilter(APPFilters appFilters);
}
