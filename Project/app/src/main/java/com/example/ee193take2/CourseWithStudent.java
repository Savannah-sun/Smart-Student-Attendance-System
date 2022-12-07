package com.example.ee193take2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"course_id", "uid"})
public class CourseWithStudent {
    public long course_id;
    public long uid;
}
