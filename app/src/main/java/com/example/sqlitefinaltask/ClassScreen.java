package com.example.sqlitefinaltask;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ClassScreen extends AppCompatActivity {
    ListView lv_classes;
    ClassAdapter classAdapter;
    ArrayList<Class> classes;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_screen);
        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        lv_classes = findViewById(R.id.lv_classes);
        classes = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from tbl_class", null);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String teacher = cursor.getString(2);

            System.out.println(id);
            System.out.println(name);
            System.out.println(teacher);

            Class c = new Class(id, name, teacher);
            classes.add(c);
        }
        cursor.close();

        classAdapter = new ClassAdapter(classes, ClassScreen.this);
        lv_classes.setAdapter(classAdapter);
        lv_classes.setOnItemClickListener((adapterView, view, i, l) -> {});
    }
}