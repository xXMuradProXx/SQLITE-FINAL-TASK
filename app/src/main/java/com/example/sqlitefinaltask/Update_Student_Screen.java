package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Student_Screen extends AppCompatActivity {
    SQLiteDatabase db;

    EditText et_id;
    EditText et_name;
    EditText et_surname;
    EditText et_class;
    EditText et_average;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_screen);

        getSupportActionBar().hide();
        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        et_surname = findViewById(R.id.et_surname);
        et_class = findViewById(R.id.et_class);
        et_average = findViewById(R.id.et_average);

        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id = Integer.parseInt(et_id.getText().toString());
                    String name = et_name.getText().toString();
                    String surname = et_surname.getText().toString();
                    String className = et_class.getText().toString();
                    int avg = Integer.parseInt(et_average.getText().toString());

                    Student student = new Student(id, name, surname, className, avg);

                    Utils.updateStudent(student, db);
                    Toast.makeText(Update_Student_Screen.this, "The information about the student was successfully updated", Toast.LENGTH_SHORT).show();
                }
                catch(NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(Update_Student_Screen.this, "The Student not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}