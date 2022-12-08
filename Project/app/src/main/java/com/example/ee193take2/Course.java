package com.example.ee193take2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "course_table")
public class Course {
    @PrimaryKey(autoGenerate = true) public int course_id;
    public String class_name;
    public List<String> dates;

}
