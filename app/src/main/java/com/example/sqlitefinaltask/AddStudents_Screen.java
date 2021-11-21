package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudents_Screen extends AppCompatActivity {
    EditText et_name;
    EditText et_surname;
    EditText et_class;
    EditText et_average;
    Button btn_submit;

    SQLiteDatabase db;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students_screen);

        et_name = findViewById(R.id.et_name);
        et_surname = findViewById(R.id.et_surname);
        et_class = findViewById(R.id.et_class);
        et_average = findViewById(R.id.et_average);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(view -> {
            try {
                String name = et_name.getText().toString();
                String surname = et_surname.getText().toString();
                int classId = Integer.parseInt(et_class.getText().toString());
                int avg = Integer.parseInt(et_average.getText().toString());

                student = new Student(name, surname, classId, avg);

                Utils.addStudent(student, db);
            }
            catch(NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Please enter number fot class and average", Toast.LENGTH_LONG).show();
            }
        });
    }
}