package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Show_Students_By_Name extends AppCompatActivity {

    TextView tv_students_list_by_name;
    ListView lv_students_by_name;

    Intent intent;
    SQLiteDatabase db;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_students_by_name);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        intent = getIntent();

        tv_students_list_by_name = findViewById(R.id.tv_students_list_by_name);
        lv_students_by_name = findViewById(R.id.lv_students_by_name);

        String name = intent.getStringExtra(Utils.INTENT_KEY_STUDENT_FIRST_NAME);
        students = Utils.showStudentsByName(name, db);

        studentAdapter = new StudentAdapter(students, Show_Students_By_Name.this);
        lv_students_by_name.setAdapter(studentAdapter);
    }
}