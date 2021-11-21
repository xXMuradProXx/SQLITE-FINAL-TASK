package com.example.sqlitefinaltask;

public class Student {
    private int id;
    private String name;
    private String surname;
    private int classId;
    private int avg;

    public Student(String name, String surname, int classId,  int avg) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.classId = classId;
        this.avg = avg;
    }

    public Student(int id, String name, String surname, int classId, int avg) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.classId = classId;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
