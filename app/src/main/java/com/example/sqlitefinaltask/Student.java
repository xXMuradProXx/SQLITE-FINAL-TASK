package com.example.sqlitefinaltask;

import androidx.annotation.NonNull;

public class Student {
    private String name;
    private String surname;
    private String st_class;
    private int avg;
    private int id;

    public Student(int id, String name, String surname, String st_class, int avg) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.st_class = st_class;
        this.avg = avg;
    }

    public Student(String name, String surname, String st_class, int avg) {
        this.name = name;
        this.surname = surname;
        this.st_class = st_class;
        this.avg = avg;
        id = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSt_class() {
        return st_class;
    }

    public void setSt_class(String st_class) {
        this.st_class = st_class;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public int getId() {return id;}

    public void setId(int id){this.id = id;}

    @NonNull
    @Override
    public String toString() {
        return("ID: " + id + "\n Name: " + name + "\n Surname: " + surname + "\n Class: " + st_class + "\n Average: " + avg + "\n");
    }
}
