package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TeacherScreen extends AppCompatActivity {
    ListView lv_teachers;
    TeacherAdapter teacherAdapter;
    ArrayList<Teacher> teachers;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_screen);
        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        lv_teachers = findViewById(R.id.lv_teachers);
        teachers = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from tbl_teacher", null);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String subject = cursor.getString(3);

            System.out.println(id);
            System.out.println(name);
            System.out.println(surname);
            System.out.println(subject);

            Teacher teacher = new Teacher(id, name, surname, subject);
            teachers.add(teacher);
        }
        cursor.close();

        teacherAdapter = new TeacherAdapter(teachers, TeacherScreen.this);
        lv_teachers.setAdapter(teacherAdapter);
        lv_teachers.setOnItemClickListener((adapterView, view, i, l) -> {});
    }
}