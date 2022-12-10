package com.example.ee193take2.ui.database;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;


@Entity(tableName = "attendance_table",
        indices = {@Index(value = {"student_id","courseOffering_id","date"})},
        primaryKeys = {"student_id","courseOffering_id","date"},
        foreignKeys = {
                @ForeignKey(entity = Student.class,
                        parentColumns = "student_id",
                        childColumns = "student_id",
                        onDelete = CASCADE),
                @ForeignKey(entity = CalendarCourseOffering.class,
                        parentColumns = {"courseOffering_id","date"},
                        childColumns = {"courseOffering_id","date"},
                        onDelete = CASCADE)})
public class Attendance {
    private int student_id;
    private int courseOffering_id;
    @NotNull
    private Date date;
    @ColumnInfo
    private boolean attend;

    public Attendance(int student_id, int courseOffering_id, Date date, boolean attend) {
        this.student_id = student_id;
        this.courseOffering_id = courseOffering_id;
        this.date = date;
        this.attend = attend;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttend() {
        return attend;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "student_id=" + student_id +
                ", courseOffering_id=" + courseOffering_id +
                ", date=" + date +
                ", attend=" + attend +
                '}';
    }
}
