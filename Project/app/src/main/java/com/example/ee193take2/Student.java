package com.example.ee193take2;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;


@Entity(tableName = "student_table")
public class Student {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public String getLastName() {return this.lastName;}

    public Student(String last, String first){
        firstName = first;
        lastName = last;
    }

    public Student (){
        firstName ="";
        lastName = "";
    }
}

