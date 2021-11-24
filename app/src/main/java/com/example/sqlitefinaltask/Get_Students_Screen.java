package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Get_Students_Screen extends AppCompatActivity {
    TextView tv_students_list;
    ListView lv_all_students;
    Switch switch_by_subject;

    SQLiteDatabase db;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students_screen);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        Intent intent = getIntent();
        String table = intent.getStringExtra(Utils.INTENT_KEY_GET_STUDENTS);

        tv_students_list = findViewById(R.id.tv_students_list);
        lv_all_students = findViewById(R.id.lv_all_students);

        if(table.equals(Utils.INTENT_KEY_GET_STUDENTS_BY_NAME)){
            String name = intent.getStringExtra(Utils.INTENT_KEY_STUDENT_NAME);
            students = Utils.getStudentsByName(name, db);
        }
        else if(table.equals(Utils.INTENT_KEY_GET_STUDENTS_BY_CLASS)) {
            String className = intent.getStringExtra(Utils.INTENT_KEY_STUDENT_CLASS_NAME);
            students = Utils.getStudentsByClass(className, db);
        }
        else if(table.equals(Utils.INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE)){
            int avg = intent.getIntExtra(Utils.INTENT_KEY_STUDENT_AVERAGE, 0);
            students = Utils.getStudentsByHigherAvg(avg, db);
        }
        else {
            students = new ArrayList<>();

            Cursor cursor = db.rawQuery("select * from tbl_student", null);
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                String className = cursor.getString(3);
                int avg = cursor.getInt(4);

                System.out.println(id);
                System.out.println(name);
                System.out.println(surname);
                System.out.println(className);
                System.out.println(avg);

                Student student = new Student(name, surname, className, avg);
                students.add(student);
            }
        }

        studentAdapter = new StudentAdapter(students, Get_Students_Screen.this);
        lv_all_students.setAdapter(studentAdapter);
        lv_all_students.setOnItemClickListener((adapterView, view, i, l) -> {});

        switch_by_subject = findViewById(R.id.switch_by_subject);
        switch_by_subject.setOnCheckedChangeListener((compoundButton, b) -> {

        });
    }
}