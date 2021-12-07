package com.example.sqlitefinaltask;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String className;
    private int avg;
    private String subject;

    public Student(String name, String surname, String className,  int avg) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.className = className;
        this.avg = avg;
    }

    public Student(int id, String name, String surname, String className, int avg) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.className = className;
        this.avg = avg;
    }

    public Student(int id, String name, String surname, String className, int avg, String subject) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.className = className;
        this.avg = avg;
        this.subject = subject;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
