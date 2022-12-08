package com.example.myapplication;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@androidx.room.Database(entities = {TODOTask.class, APPFilters.class, APPSettings.class}, version = 3)
public abstract class Database extends RoomDatabase {

    public static Database INSTANCE = null;
    public abstract DAO dao();
    public abstract APPFiltersDAO appFiltersDAO();
    public abstract SettingsDAO settingsDAO();


    public static Database getDatabase(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context, Database.class, "Database").allowMainThreadQueries().build();
        }
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
