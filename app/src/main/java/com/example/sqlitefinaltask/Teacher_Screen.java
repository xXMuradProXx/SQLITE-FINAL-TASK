package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Teacher_Screen extends AppCompatActivity {
    SQLiteDatabase db;

    TextView tv_teachers_list;
    Switch switch_teachers_by_subject;

    ListView lv_unsorted_teachers;
    TeacherAdapter unsortedTeacherAdapter;
    ArrayList<Teacher> teacherArrayList;

    ListView lv_sorted_teachers;
    TeacherAdapter sortedTeacherAdapter;
    ArrayList<Teacher> sorted;

    RecyclerView rv_teachers;
    //TeacherListAdapterForRecyclerView teacherListAdapterForRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_teacher_screen);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        tv_teachers_list = findViewById(R.id.tv_teachers_list);

        teacherArrayList = new ArrayList<>();

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
            teacherArrayList.add(teacher);
        }
        for(int i = 0; i < teacherArrayList.size(); i++){
            Log.d("teachers", teacherArrayList.get(i).getId() + " | "
                    + teacherArrayList.get(i).getName() + " | "
                    + teacherArrayList.get(i).getSurname() + " | "
                    + teacherArrayList.get(i).getSubject());
        }

        sorted = Utils.sortTeachers(teacherArrayList);

        //lv_unsorted_teachers = findViewById(R.id.lv_unsorted_teachers);
        //lv_unsorted_teachers.clearAnimation();
        //lv_unsorted_teachers.setVisibility(View.VISIBLE);
        //unsortedTeacherAdapter = new TeacherAdapter(teacherArrayList, Teacher_Screen.this);
        //lv_unsorted_teachers.setAdapter(unsortedTeacherAdapter);

        // Get a handle to the RecyclerView.
        /*rv_teachers = findViewById(R.id.rv_teachers);
        // Create an adapter and supply the data to be displayed.
        teacherListAdapterForRecyclerView = new TeacherListAdapterForRecyclerView(getApplicationContext(), teacherArrayList);
        // Connect the adapter with the RecyclerView.
        rv_teachers.setAdapter(teacherListAdapterForRecyclerView);
        // Give the RecyclerView a default layout manager.
        rv_teachers.setLayoutManager(new LinearLayoutManager(this));*/

        /*lv_sorted_teachers = findViewById(R.id.lv_sorted_teachers);
        lv_sorted_teachers.clearAnimation();
        lv_sorted_teachers.setVisibility(View.GONE);
        sortedTeacherAdapter = new TeacherAdapter(sorted, Teacher_Screen.this);
        lv_sorted_teachers.setAdapter(sortedTeacherAdapter);

        switch_teachers_by_subject = findViewById(R.id.switch_teachers_by_subject);
        switch_teachers_by_subject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(getApplicationContext(), "isChecked: " + switch_teachers_by_subject.isChecked(), Toast.LENGTH_SHORT).show();
                if(b){
                    //lv_unsorted_teachers.setVisibility(View.GONE);
                    lv_sorted_teachers.setVisibility(View.VISIBLE);
                }
                else {
                    //lv_unsorted_teachers.setVisibility(View.VISIBLE);
                    lv_sorted_teachers.setVisibility(View.INVISIBLE);
                }
            }
        });
        switch_teachers_by_subject.setChecked(false);*/
    }
}