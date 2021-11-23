package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    ListView lv;
    SQLiteDatabase db;
    StudentAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        db = openOrCreateDatabase(Utils.DB_name, MODE_PRIVATE, null);
        lv = findViewById(R.id.lv);
        fab = findViewById(R.id.fab);

        ArrayList<Student> students = Utils.getStudents(db);

        adapter = new StudentAdapter(students, TableActivity.this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int st_id = students.get(position).getId();
                Intent intent = new Intent(TableActivity.this, EditScreen.class);
                intent.putExtra("id", st_id);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TableActivity.this, EditScreen.class));
            }
        });




    }


}