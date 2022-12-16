package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CacheDAO {

    @Insert
    void addCacheEntry(cacheEntry cache);

    @Query("SELECT * FROM CACHE")
    List<cacheEntry> getEntireCache();

    @Query("SELECT * FROM CACHE WHERE id = :ID LIMIT 1")
    cacheEntry getCacheEntry(String ID);

    @Delete
    void deleteCacheEntry(cacheEntry toDelete);

    @Update
    void updateCacheEntry(cacheEntry toUpdate);

}
