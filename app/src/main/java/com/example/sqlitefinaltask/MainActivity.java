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

    Button btn_teachers;
    Button btn_classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        et_class = findViewById(R.id.et_class);

        btn_get_students_by_class = findViewById(R.id.btn_get_students_by_class);
        btn_get_students_by_class.setOnClickListener(view -> {

            String className = et_class.getText().toString().trim();
            students = Utils.getStudentsByClass(className, db);

            if(className.isEmpty())
                Toast.makeText(getApplicationContext(), "Please enter the class", Toast.LENGTH_SHORT).show();
            else if(!Utils.ifClassExists(className, db))
                Toast.makeText(MainActivity.this, "Class with this name doesn't exist", Toast.LENGTH_SHORT).show();
            else if(students.isEmpty())
                Toast.makeText(MainActivity.this, "There are no students in this class", Toast.LENGTH_SHORT).show();
            else{
                Intent intent = new Intent(MainActivity.this, Get_Students_Screen.class);
                intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS_BY_CLASS);
                intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS_BY_CLASS_STUDENT_CLASS_NAME, className);
                startActivity(intent);
            }
        });

        et_average = findViewById(R.id.et_average);

        btn_get_students_by_higher_average = findViewById(R.id.btn_get_students_by_higher_average);
        btn_get_students_by_higher_average.setOnClickListener(view -> {

            int avg = Integer.parseInt(et_average.getText().toString().trim());
            students = Utils.getStudentsByHigherAvg(avg, db);

            if(et_average.getText().toString().isEmpty())
                Toast.makeText(getApplicationContext(), "Please enter the average mark", Toast.LENGTH_SHORT).show();
            else if(students.isEmpty())
                Toast.makeText(MainActivity.this, "There are no students with higher average mark", Toast.LENGTH_SHORT).show();
            else{
                Intent intent = new Intent(MainActivity.this, Get_Students_Screen.class);
                intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE);
                intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE_STUDENT_AVERAGE ,avg);
                startActivity(intent);
            }
        });

        et_name = findViewById(R.id.et_name);

        btn_get_students_by_name = findViewById(R.id.btn_get_students_by_name);
        btn_get_students_by_name.setOnClickListener(view -> {

            String name = et_name.getText().toString().trim();
            students = Utils.getStudentsByName(name, db);

            if(name.isEmpty())
                Toast.makeText(getApplicationContext(), "Please enter the name", Toast.LENGTH_SHORT).show();
            else if(students.isEmpty())
                Toast.makeText(MainActivity.this, "Students with this name are not found", Toast.LENGTH_SHORT).show();
            else{
                Intent intent = new Intent(MainActivity.this, Get_Students_Screen.class);
                intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS, Utils.INTENT_KEY_GET_STUDENTS_BY_NAME);
                intent.putExtra(Utils.INTENT_KEY_GET_STUDENTS_BY_NAME_STUDENT_NAME, name);
                startActivity(intent);
            }
        });

        btn_teachers = findViewById(R.id.btn_teachers);
        btn_teachers.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, TeacherScreen.class)));

        btn_classes = findViewById(R.id.btn_classes);
        btn_classes.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ClassScreen.class)));
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
            intent.putExtra(Utils.INTENT_KEY_CHECKED, false);
        }
        if(selectedId == R.id.details){
            intent = new Intent(MainActivity.this, Details_Screen.class);
        }
        startActivity(intent);
        return true;
    }

}