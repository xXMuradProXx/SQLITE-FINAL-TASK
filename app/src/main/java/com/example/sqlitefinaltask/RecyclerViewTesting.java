package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewTesting extends AppCompatActivity {
    TextView tv_students_list;
    RecyclerView rv_students;
    StudentListAdapter studentListAdapter;

    SQLiteDatabase db;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_testing);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        tv_students_list = findViewById(R.id.tv_students_list);

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

        // Get a handle to the RecyclerView.
        rv_students = findViewById(R.id.rv_students);
        // Create an adapter and supply the data to be displayed.
        studentListAdapter = new StudentListAdapter(getApplicationContext(), students);
        // Connect the adapter with the RecyclerView.
        rv_students.setAdapter(studentListAdapter);
        // Give the RecyclerView a default layout manager.
        rv_students.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }
}