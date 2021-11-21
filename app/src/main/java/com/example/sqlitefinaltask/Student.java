package com.example.sqlitefinaltask;

public class Student {
    String name;
    String surname;
    int avg;

    public Student(String name, String surname, int avg) {
        this.name = name;
        this.surname = surname;
        this.avg = avg;
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

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }
}
