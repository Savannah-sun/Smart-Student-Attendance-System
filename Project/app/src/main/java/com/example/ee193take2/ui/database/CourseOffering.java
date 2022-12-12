package com.example.ee193take2.ui.database;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "courseOffering_table",
        foreignKeys = @ForeignKey(entity = Course.class,
                parentColumns = "course_id",
                childColumns = "course_id",
                onDelete = CASCADE))
public class CourseOffering {
    @PrimaryKey(autoGenerate = true)
    private int cid;

    @ColumnInfo
    private int course_id;

    @ColumnInfo
    private String classroom;

    @ColumnInfo
    private int studentNum;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    @Override
    public String toString() {
        return "CourseOffering{" +
                "cid='" + cid + '\'' +
                ", course_id=" + course_id +
                ", classroom='" + classroom + '\'' +
                ", studentNum=" + studentNum +
                '}';
    }

    public CourseOffering(int course_id, String classroom, int studentNum) {
        this.course_id = course_id;
        this.classroom = classroom;
        this.studentNum = studentNum;
    }
}
