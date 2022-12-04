package com.example.ee193take2;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

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

    @ColumnInfo(name = "classes")
    private List<String> classes;


    public String getLastName() {return this.lastName;}

    public  String getFirstName() {return this.firstName;}

    public List<Boolean> getAttendance() {return this.attendance;}

    public void setAttendance(List<Boolean> attendance) {this.attendance = attendance;}

    public List<String> getClasses() {return this.classes;}

    public void setClasses(List<String> classes) {this.classes = classes;}

    public Student(String last, String first){
        firstName = first;
        lastName = last;
    }

    public Student (){
        firstName ="";
        lastName = "";
    }
}

