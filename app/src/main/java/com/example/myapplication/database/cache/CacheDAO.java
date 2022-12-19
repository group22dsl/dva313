package com.example.myapplication.database.cache;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CacheDAO {

    @Insert
    void addCacheEntry(CacheEntry cache);

    @Query("SELECT * FROM CACHE")
    List<CacheEntry> getEntireCache();

    @Query("SELECT * FROM CACHE WHERE id = :ID LIMIT 1")
    CacheEntry getCacheEntry(String ID);

    @Query("SELECT * FROM CACHE WHERE priority = 'Low'")
    List<CacheEntry> getLowPriorityCache();

    @Query("SELECT * FROM CACHE WHERE priority = 'Medium'")
    List<CacheEntry> getMediumPriorityCache();

    @Query("SELECT * FROM CACHE WHERE priority = 'High'")
    List<CacheEntry> getHighPriorityCache();

    @Query("SELECT * FROM CACHE WHERE priority = 'Critical'")
    List<CacheEntry> getCriticalPriorityCache();

    @Query("DELETE FROM CACHE")
    void resetCache();

    @Delete
    void deleteCacheEntry(CacheEntry toDelete);

    @Update
    void updateCacheEntry(CacheEntry toUpdate);

}
