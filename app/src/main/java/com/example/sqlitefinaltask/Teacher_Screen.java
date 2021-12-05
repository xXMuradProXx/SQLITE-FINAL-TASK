package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class Teacher_Screen extends AppCompatActivity {
    SQLiteDatabase db;

    TextView tv_teachers_list;
    Switch switch_teachers_by_subject;
    ListView lv_teachers;
    TeacherAdapter teacherAdapter;
    TeacherAdapter sortedTeacherAdapter;
    ArrayList<Teacher> teachers;
    ArrayList<Teacher> sorted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_screen);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        tv_teachers_list = findViewById(R.id.tv_teachers_list);
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
        sorted = Utils.sortTeachers(teachers);

        teacherAdapter = new TeacherAdapter(teachers, Teacher_Screen.this);
        sortedTeacherAdapter = new TeacherAdapter(sorted, Teacher_Screen.this);

        lv_teachers.setAdapter(teacherAdapter);

        switch_teachers_by_subject = findViewById(R.id.switch_teachers_by_subject);
        switch_teachers_by_subject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    lv_teachers.setAdapter(sortedTeacherAdapter);
                }
                else {
                    lv_teachers.setAdapter(teacherAdapter);
                }
            }
        });
    }
}