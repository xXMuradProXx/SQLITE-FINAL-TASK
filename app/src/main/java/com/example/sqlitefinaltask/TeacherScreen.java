package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

public class TeacherScreen extends AppCompatActivity {
    ListView lv_teachers;
    TeacherAdapter teacherAdapter;
    ArrayList<Teacher> teachers;

    ListView lv_sorted_teachers;
    TeacherAdapter sortedTeacherAdapter;
    ArrayList<Teacher> sorted;

    Switch switch_teachers_by_subject;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_screen);
        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

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
        for(int i=0; i < teachers.size(); i++){
            Log.d("check", teachers.get(i).getId() + " | "
                    + teachers.get(i).getName() + " | "
                    + teachers.get(i).getSurname() + " | "
                    + teachers.get(i).getSubject());
        }
        lv_teachers = findViewById(R.id.lv_teachers);
        teacherAdapter = new TeacherAdapter(teachers, TeacherScreen.this);
        lv_teachers.setAdapter(teacherAdapter);
        lv_teachers.setVisibility(View.VISIBLE);


        sorted = Utils.sortTeachers(teachers);
        lv_sorted_teachers = findViewById(R.id.lv_sorted_teachers);
        sortedTeacherAdapter = new TeacherAdapter(sorted, TeacherScreen.this);
        lv_sorted_teachers.setAdapter(sortedTeacherAdapter);
        lv_sorted_teachers.setVisibility(View.INVISIBLE);

        switch_teachers_by_subject = findViewById(R.id.switch_teachers_by_subject);
        switch_teachers_by_subject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    lv_sorted_teachers.setVisibility(View.VISIBLE);
                    lv_teachers.setVisibility(View.INVISIBLE);
                }
                else {
                    lv_sorted_teachers.setVisibility(View.INVISIBLE);
                    lv_teachers.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}