package com.example.ssas;

public class Class {
    private int id;
    private String name;
    private boolean status;
    private int numberOfOffering;

    public Class(int id, String name, boolean status, int numberOfOffering) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.numberOfOffering = numberOfOffering;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumberOfOffering() {
        return numberOfOffering;
    }

    public void setNumberOfOffering(int numberOfOffering) {
        this.numberOfOffering = numberOfOffering;
    }
}
