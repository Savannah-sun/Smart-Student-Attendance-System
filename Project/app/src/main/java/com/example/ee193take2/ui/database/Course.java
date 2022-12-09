package com.example.ee193take2.ui.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "course_table")
public class Course {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "course_id")
    private int course_id;

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", class_name='" + class_name + '\'' +
                ", status=" + status +
                ", number_offerings=" + number_offerings +
                '}';
    }

    @ColumnInfo
    private String class_name;

    @ColumnInfo
    private boolean status;

    @ColumnInfo
    private int number_offerings;

    public Course(int course_id, String class_name, boolean status, int number_offerings) {
        this.course_id = course_id;
        this.class_name = class_name;
        this.status = status;
        this.number_offerings = number_offerings;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumber_offerings() {
        return number_offerings;
    }

    public void setNumber_offerings(int number_offerings) {
        this.number_offerings = number_offerings;
    }
}
