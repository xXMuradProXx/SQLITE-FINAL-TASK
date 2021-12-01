package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    Button btn_enter;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView = findViewById(R.id.imageView);
        btn_enter = findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        Utils.deleteAllTables(db);
        Utils.createAllTables(db);
        Utils.addDefaultStudents(db);
        Utils.addDefaultClasses(db);
        Utils.addDefaultTeachers(db);
    }
}