package com.example.sqlitefinaltask;

public class Teacher {
    private int id;
    private String first_name;
    private String last_name;
    private String subject;

    public Teacher(int id, String first_name, String last_name, String subject) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.subject = subject;
    }

    public Teacher(String first_name, String last_name, String subject) {
        this.id = 0;
        this.first_name = first_name;
        this.last_name = last_name;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
