package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void addTask(TODOTask todoTask);

    @Query("SELECT * FROM todo_tasks")
    List<TODOTask> getAllTasks();

    @Query("SELECT * FROM todo_tasks WHERE id = :taskId LIMIT 1")
    TODOTask getTask (int taskId);

    @Delete
    void deleteTask(TODOTask todoTask);

    @Update
    void updateTask(TODOTask todoTask);

}
