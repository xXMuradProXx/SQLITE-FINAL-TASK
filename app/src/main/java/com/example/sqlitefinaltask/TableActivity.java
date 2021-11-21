package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    ListView lv;
    SQLiteDatabase db;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        db = openOrCreateDatabase(Utils.DB_name, MODE_PRIVATE, null);
        lv = findViewById(R.id.lv);

        ArrayList<Student> students = Utils.getStudents(db);

        adapter = new StudentAdapter(students, TableActivity.this);


    }


}