package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Show_All_Students extends AppCompatActivity {

    TextView tv_students_list;
    ListView lv_students;

    SQLiteDatabase db;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_students);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        tv_students_list = findViewById(R.id.tv_students_list);
        lv_students = findViewById(R.id.lv_students);

        students = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String first_name = cursor.getString(1);
            String last_name = cursor.getString(2);
            String class_name = cursor.getString(3);
            int avg = cursor.getInt(4);

            Student student = new Student(id, first_name, last_name, class_name, avg);
            students.add(student);
        }

        cursor.close();

        studentAdapter = new StudentAdapter(students, Show_All_Students.this);
        lv_students.setAdapter(studentAdapter);

    }

}