package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    EditText et_class;
    Button btn_get_students_by_class;

    EditText et_average;
    Button btn_get_students_with_by_higher_average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        et_class = findViewById(R.id.et_class);

        btn_get_students_by_class = findViewById(R.id.btn_get_students_by_class);
        btn_get_students_by_class.setOnClickListener(view -> {
            String className = et_class.getText().toString();
            Intent intent = new Intent(MainActivity.this, Get_Students_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS_BY_CLASS);
            intent.putExtra(Utils.INTENT_KEY_STUDENT_CLASS_NAME, className);
            startActivity(intent);
        });

        et_average = findViewById(R.id.et_average);

        btn_get_students_with_by_higher_average = findViewById(R.id.btn_get_students_by_higher_average);
        btn_get_students_with_by_higher_average.setOnClickListener(view -> {
            int avg = Integer.valueOf(et_average.getText().toString());
            Intent intent = new Intent(MainActivity.this, Get_Students_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE);
            intent.putExtra(Utils.INTENT_KEY_STUDENT_AVERAGE ,avg);
            startActivity(intent);
        });

        Utils.deleteAllTables(db);
        Utils.createAllTables(db);
        Utils.addDefaultStudents(db);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int selectedId = item.getItemId();
        Intent intent = new Intent();

        if(selectedId == R.id.all_students){
            intent = new Intent(MainActivity.this, Get_Students_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS);
        }
        if(selectedId == R.id.add_students){
            intent = new Intent(MainActivity.this, AddStudents_Screen.class);
        }
        if(selectedId == R.id.details){
            intent = new Intent(MainActivity.this, Details_Screen.class);
        }
        startActivity(intent);
        return true;
    }

}