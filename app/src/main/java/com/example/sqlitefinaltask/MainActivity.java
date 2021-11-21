package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_start);
        db = openOrCreateDatabase(Utils.DB_name, MODE_PRIVATE, null);

        Student s = new Student("rafael", "zagha", " i1" ,  45.7);
        Student t = new Student("murad", "ragimli", " i2" ,  30.5);
        Student p = new Student("roni", "ruben", " i9" ,  74.9);

        Utils.createTables(db);
        Utils.resetTables(db);

        Utils.addStudent(s, db);
        Utils.addStudent(t, db);
        Utils.addStudent(p, db);

    }

}