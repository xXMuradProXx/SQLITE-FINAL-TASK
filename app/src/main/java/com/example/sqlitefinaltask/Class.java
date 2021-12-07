package com.example.sqlitefinaltask;

public class Class {
    private int id;
    private String class_name;
    private String teacher_name;

    public Class(int id, String class_name, String teacher_name) {
        this.id = id;
        this.class_name = class_name;
        this.teacher_name = teacher_name;
    }

    public Class(String class_name, String teacher_name) {
        this.id = 0;
        this.class_name = class_name;
        this.teacher_name = teacher_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}
