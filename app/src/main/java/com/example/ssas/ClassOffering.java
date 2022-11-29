package com.example.ssas;

import java.util.Date;
import java.util.List;

public class ClassOffering {
    private List<Date> dates;
    private int numbersOfStudent;
    private String classroom;

    public ClassOffering(List<Date> dates, int numbersOfStudent, String classroom) {
        this.dates = dates;
        this.numbersOfStudent = numbersOfStudent;
        this.classroom = classroom;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public int getNumbersOfStudent() {
        return numbersOfStudent;
    }

    public void setNumbersOfStudent(int numbersOfStudent) {
        this.numbersOfStudent = numbersOfStudent;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
