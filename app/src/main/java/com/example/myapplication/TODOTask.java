package com.example.myapplication;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_tasks")
public class TODOTask {

    //primary key is must for every table in the database
    @PrimaryKey(autoGenerate = true)
    private int id;

    //creating these functions, variables and UI just for testing database, we will change them in the future to adopt it to our project

    private String title;
    private String description;

    @Ignore
    public TODOTask(String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public TODOTask(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
