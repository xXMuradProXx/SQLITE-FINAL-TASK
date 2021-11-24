package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ArrayList<Student> students;

    EditText et_class;
    Button btn_get_students_by_class;

    EditText et_average;
    Button btn_get_students_by_higher_average;

    EditText et_name;
    Button btn_get_students_by_name;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        Utils.deleteAllTables(db);
        Utils.createAllTables(db);
        Utils.addDefaultStudents(db);

        et_class = findViewById(R.id.et_class);

        btn_get_students_by_class = findViewById(R.id.btn_get_students_by_class);
        btn_get_students_by_class.setOnClickListener(view -> {
            String className = et_class.getText().toString().trim();
            Intent intent = new Intent(MainActivity.this, Get_Students_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS_BY_CLASS);
            intent.putExtra(Utils.INTENT_KEY_STUDENT_CLASS_NAME, className);
            students = Utils.getStudentsByClass(className, db);
            if(students.isEmpty())
                Toast.makeText(MainActivity.this, "There are no students in this class", Toast.LENGTH_SHORT).show();
            else
                startActivity(intent);
        });

        et_average = findViewById(R.id.et_average);

        btn_get_students_by_higher_average = findViewById(R.id.btn_get_students_by_higher_average);
        btn_get_students_by_higher_average.setOnClickListener(view -> {
            int avg = Integer.parseInt(et_average.getText().toString().trim());
            Intent intent = new Intent(MainActivity.this, Get_Students_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE);
            intent.putExtra(Utils.INTENT_KEY_STUDENT_AVERAGE ,avg);
            students = Utils.getStudentsByHigherAvg(avg, db);
            if(students.isEmpty())
                Toast.makeText(MainActivity.this, "There are no students with higher average mark", Toast.LENGTH_SHORT).show();
            else
                startActivity(intent);
        });

        et_name = findViewById(R.id.et_name);

        btn_get_students_by_name = findViewById(R.id.btn_get_students_by_name);
        btn_get_students_by_name.setOnClickListener(view -> {
            String name = et_name.getText().toString().trim();
            Intent intent = new Intent(MainActivity.this, Get_Students_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS_BY_NAME);
            intent.putExtra(Utils.INTENT_KEY_STUDENT_NAME, name);
            students = Utils.getStudentsByName(name, db);
            if(students.isEmpty())
                Toast.makeText(MainActivity.this, "Students with this name are not found", Toast.LENGTH_SHORT).show();
            else
                startActivity(intent);
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, Teacher_Screen.class)));
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

        if(selectedId == R.id.add_student){
            intent = new Intent(MainActivity.this, AddStudent_Screen.class);
        }
        if(selectedId == R.id.delete_student){
            intent = new Intent(MainActivity.this,  Delete_Student_Screen.class);
        }
        if(selectedId == R.id.update_student){
            intent = new Intent(MainActivity.this, Update_Student_Screen.class);
        }
        if(selectedId == R.id.all_students){
            intent = new Intent(MainActivity.this, Get_Students_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS);
        }
        if(selectedId == R.id.details){
            intent = new Intent(MainActivity.this, Details_Screen.class);
        }
        startActivity(intent);
        return true;
    }

}