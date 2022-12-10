package com.example.ee193take2.ui.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "studentclassoffering_table",
        primaryKeys = {"courseOffering_id","student_id"},
        foreignKeys = {
                @ForeignKey(entity = Student.class,
                        parentColumns = "student_id",
                        childColumns = "student_id"),
                @ForeignKey(entity = CourseOffering.class,
                        parentColumns = "cid",
                        childColumns = "courseOffering_id")})
public class StudentClassOffering {
    public int student_id;
    public int courseOffering_id;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourseOffering_id() {
        return courseOffering_id;
    }

    public void setCourseOffering_id(int courseOffering_id) {
        this.courseOffering_id = courseOffering_id;
    }

    public StudentClassOffering(int student_id, int courseOffering_id) {
        this.student_id = student_id;
        this.courseOffering_id = courseOffering_id;
    }

    @Override
    public String toString() {
        return "StudentClassOffering{" +
                "student_id=" + student_id +
                ", courseOffering_id=" + courseOffering_id +
                '}';
    }
}
