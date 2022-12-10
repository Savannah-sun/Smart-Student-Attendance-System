package com.example.ee193take2.ui.database;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.Relation;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;


@Entity(tableName = "student_table",indices={@Index({"first_name","last_name"})})
public class Student {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "student_id")
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

//    @ColumnInfo(name = "attendance")
//    private List<Boolean> attendance;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "status")
    private String status;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ColumnInfo(name = "payment")
    private String payment;

    public Student(int uid, String firstName, String lastName, String email, String status, String payment) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.payment = payment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}

