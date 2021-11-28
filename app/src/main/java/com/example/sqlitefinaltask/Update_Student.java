package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update_Student extends AppCompatActivity {
    SQLiteDatabase db;

    EditText et_id;
    EditText et_firstname;
    EditText et_lastname;
    EditText et_class;
    EditText et_avg;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        et_id = findViewById(R.id.et_id);
        et_firstname = findViewById(R.id.et_firstname);
        et_lastname = findViewById(R.id.et_lastname);
        et_class = findViewById(R.id.et_class);
        et_avg = findViewById(R.id.et_avg);

        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(et_id.getText().toString());
                String name = et_firstname.getText().toString();
                String surname = et_lastname.getText().toString();
                String className = et_class.getText().toString();
                int avg = Integer.parseInt(et_avg.getText().toString());

                Student student = new Student(id, name, surname, className, avg);

                Utils.updateStudent(student, db);

                Intent intent = new Intent(Update_Student.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}