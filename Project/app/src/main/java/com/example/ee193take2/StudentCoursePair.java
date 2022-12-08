package com.example.ee193take2;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class StudentCoursePair {
    @Embedded Student student;
    @Relation(
            parentColumn = "uid",
            entityColumn = "course_id",
            associateBy =@Junction(CourseWithStudent.class))
    public List<Course> courses;
}