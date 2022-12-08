package com.example.ee193take2;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.Relation;

import java.util.Collections;
import java.util.List;


@Entity(tableName = "student_table")
public class Student {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "attendance")
    private List<Boolean> attendance;



    public String getLastName() {return this.lastName;}

    public  String getFirstName() {return this.firstName;}

    public List<Boolean> getAttendance() {return this.attendance;}

    public void setAttendance(List<Boolean> attendance) {this.attendance = attendance;}


    public Student(String last, String first){
        firstName = first;
        lastName = last;
    }

    public Student (){
        firstName ="";
        lastName = "";
        attendance = Collections.emptyList();
    }
}

