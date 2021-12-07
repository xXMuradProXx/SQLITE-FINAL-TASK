package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    EditText et_class;
    Button btn_show_students_by_class;

    EditText et_avg;
    Button btn_show_students_by_higher_average;

    EditText et_firstname;
    Button btn_show_students_by_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        Utils.deleteAllTables(db);
        Utils.createAllTables(db);
        Utils.DefaultStudents(db);
        Utils.DefaultTeachers(db);
        Utils.DefaultClasses(db);

        et_firstname = findViewById(R.id.et_firstname);

        btn_show_students_by_name = findViewById(R.id.btn_show_students_by_name);
        btn_show_students_by_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_firstname.getText().toString();

                Intent intent = new Intent(MainActivity.this, Show_Students_By_Name.class);
                intent.putExtra(Utils.INTENT_KEY_STUDENT_FIRST_NAME, name);

                startActivity(intent);
            }
        });

        et_class = findViewById(R.id.et_class);

        btn_show_students_by_class = findViewById(R.id.btn_show_students_by_class);
        btn_show_students_by_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String className = et_class.getText().toString();

                Intent intent = new Intent(MainActivity.this, Show_Students_By_Class.class);
                intent.putExtra(Utils.INTENT_KEY_STUDENT_CLASS_NAME, className);

                startActivity(intent);
            }
        });

        et_avg = findViewById(R.id.et_avg);

        btn_show_students_by_higher_average = findViewById(R.id.btn_show_students_by_higher_average);
        btn_show_students_by_higher_average.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int avg = Integer.parseInt(et_avg.getText().toString());

                Intent intent = new Intent(MainActivity.this, Show_Students_By_Average.class);
                intent.putExtra(Utils.INTENT_KEY_STUDENT_AVG, avg);

                startActivity(intent);
            }
        });

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

        if(selectedId == R.id.add_student){
            Intent intent = new Intent(MainActivity.this, Add_Student.class);
            startActivity(intent);
        }
        if(selectedId == R.id.delete_student){
            Intent intent = new Intent(MainActivity.this,  Delete_Student.class);
            startActivity(intent);
        }
        if(selectedId == R.id.update_student){
            Intent intent = new Intent(MainActivity.this, Update_Student.class);
            startActivity(intent);
        }
        if(selectedId == R.id.all_students){
            Intent intent = new Intent(MainActivity.this, Show_All_Students.class);
            startActivity(intent);
        }

        return true;
    }

}