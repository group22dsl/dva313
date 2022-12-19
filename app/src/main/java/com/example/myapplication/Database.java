package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@androidx.room.Database(entities = {TODOTask.class, cacheEntry.class, whitelistEntry.class, SettingsEntry.class}, version = 5)
public abstract class Database extends RoomDatabase {

    public static Database INSTANCE = null;
    public abstract DAO dao();
    public abstract WhitelistDAO whitelistDAO();
    public abstract CacheDAO cacheDAO();
    public abstract SettingsDAO settingsDAO();

    public static Database getDatabase(Context context)
    {
        if (INSTANCE != null) return INSTANCE;
        else return Room.databaseBuilder(context, Database.class, "Database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
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
