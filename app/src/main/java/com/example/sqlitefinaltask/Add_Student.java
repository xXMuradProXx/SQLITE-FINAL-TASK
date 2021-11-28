package com.example.sqlitefinaltask;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Student extends AppCompatActivity {
    EditText et_firstname;
    EditText et_lastname;
    EditText et_class;
    EditText et_avg;
    Button btn_submit;

    SQLiteDatabase db;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        et_firstname = findViewById(R.id.et_firstname);
        et_lastname = findViewById(R.id.et_lastname);
        et_class = findViewById(R.id.et_class);
        et_avg = findViewById(R.id.et_avg);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = et_firstname.getText().toString();
                String last_name = et_lastname.getText().toString();
                String class_name = et_class.getText().toString();
                int avg = Integer.parseInt(et_avg.getText().toString());

                student = new Student(first_name, last_name, class_name, avg);

                Utils.addStudent(student, db);

                Intent intent = new Intent(Add_Student.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}