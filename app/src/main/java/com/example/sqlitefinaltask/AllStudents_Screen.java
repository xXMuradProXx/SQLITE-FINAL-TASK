package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllStudents_Screen extends AppCompatActivity {
    TextView tv_all_students_list;
    ListView lv_all_students;

    SQLiteDatabase db;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        tv_all_students_list = findViewById(R.id.tv_all_students_list);
        lv_all_students = findViewById(R.id.lv_all_students);

        students = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            int classId = cursor.getInt(3);
            int avg = cursor.getInt(4);

            System.out.println(id);
            System.out.println(name);
            System.out.println(surname);
            System.out.println(classId);
            System.out.println(avg);

            Student student = new Student(id, name, surname, classId, avg);
            students.add(student);
        }

        studentAdapter = new StudentAdapter(students, AllStudents_Screen.this);
        lv_all_students.setAdapter(studentAdapter);
        lv_all_students.setOnItemClickListener((adapterView, view, i, l) -> {

        });
    }
}