package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Show_Students_By_Average extends AppCompatActivity {

    TextView tv_students_list_by_average;
    ListView lv_students_by_average;

    Intent intent;
    SQLiteDatabase db;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_students_by_average);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        intent = getIntent();

        tv_students_list_by_average = findViewById(R.id.tv_students_list_by_average);
        lv_students_by_average = findViewById(R.id.lv_students_by_average);

        int avg = intent.getIntExtra(Utils.INTENT_KEY_STUDENT_AVG, 0);
        students = Utils.showStudentsByHigherAvg(avg, db);

        studentAdapter = new StudentAdapter(students, Show_Students_By_Average.this);
        lv_students_by_average.setAdapter(studentAdapter);
    }
}