package com.example.ee193take2.ui.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;


@Entity(tableName = "calendar_table",
        primaryKeys = {"courseOffering_id","date"},
        foreignKeys = {
        @ForeignKey(entity = CourseOffering.class,
                parentColumns = "cid",
                childColumns = "courseOffering_id")})
public class CalendarCourseOffering {
    @NotNull
    private Date date;
    private int courseOffering_id;

    public CalendarCourseOffering(Date date, int courseOffering_id) {
        this.date = date;
        this.courseOffering_id = courseOffering_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCourseOffering_id() {
        return courseOffering_id;
    }

    public void setCourseOffering_id(int courseOffering_id) {
        this.courseOffering_id = courseOffering_id;
    }

    @Override
    public String toString() {
        return "CalendarCourseOffering{" +
                "date=" + date +
                ", courseOffering_id=" + courseOffering_id +
                '}';
    }
}
