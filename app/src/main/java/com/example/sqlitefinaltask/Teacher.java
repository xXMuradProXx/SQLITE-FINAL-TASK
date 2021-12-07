package com.example.sqlitefinaltask;

import android.os.Parcel;
import android.os.Parcelable;

public class Teacher implements Parcelable {
    private int id;
    private String name;
    private String surname;
    private String subject;

    public Teacher(int id, String name, String surname, String subject) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.subject = subject;
    }

    public Teacher(String name, String surname, String subject) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.subject = subject;
    }

    protected Teacher(Parcel in) {
        id = in.readInt();
        name = in.readString();
        surname = in.readString();
        subject = in.readString();
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(subject);
    }
}
