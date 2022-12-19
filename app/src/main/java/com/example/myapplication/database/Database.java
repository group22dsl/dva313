package com.example.myapplication.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.myapplication.DAO;
import com.example.myapplication.TODOTask;
import com.example.myapplication.database.cache.CacheDAO;
import com.example.myapplication.database.cache.CacheEntry;
import com.example.myapplication.database.settings.SettingsDAO;
import com.example.myapplication.database.settings.SettingsEntry;
import com.example.myapplication.database.whitelist.WhitelistDAO;
import com.example.myapplication.database.whitelist.WhitelistEntry;

import java.util.List;

@androidx.room.Database(
        entities = {
                TODOTask.class,
                CacheEntry.class,
                WhitelistEntry.class,
                SettingsEntry.class
        },
        version = 2)
public abstract class Database extends RoomDatabase {

    public static Database              INSTANCE    = null;
    public static List<WhitelistEntry>  whitelist   = null;
    public static List<SettingsEntry>   settings    = null;

    public abstract DAO             dao();
    public abstract WhitelistDAO    whitelistDAO();
    public abstract CacheDAO        cacheDAO();
    public abstract SettingsDAO     settingsDAO();

    public static Database getDatabase(Context context)
    {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context, Database.class, "Database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        return INSTANCE;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
