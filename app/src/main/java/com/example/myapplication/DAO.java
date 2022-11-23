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
    public void addTask(TODOTask todoTask);

    @Query("SELECT * FROM todo_tasks")
    public List<TODOTask> getAllTasks();

    @Query("SELECT * FROM todo_tasks WHERE id = :taskId LIMIT 1")
    public TODOTask getTask (int taskId);

    @Delete
    public void deleteTask(TODOTask todoTask);

    @Update
    public void updateTask(TODOTask todoTask);

}
