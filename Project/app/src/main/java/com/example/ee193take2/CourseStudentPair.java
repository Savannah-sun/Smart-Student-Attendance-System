package com.example.ee193take2;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CourseStudentPair{
        @Embedded Course course;
        @Relation(
        parentColumn = "course_id",
        entityColumn = "uid",
        associateBy =@Junction(CourseWithStudent.class))
     public List<Student> students;
}