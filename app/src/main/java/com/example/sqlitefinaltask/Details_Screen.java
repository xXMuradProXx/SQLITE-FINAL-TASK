package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Details_Screen extends AppCompatActivity {

    TextView tv_id;
    TextView tv_name;
    TextView tv_surname;
    TextView tv_class;
    TextView tv_average;
    TextView tv_subject;

    Button btn_update;
    Button btn_delete;

    Intent gotten_intent;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        gotten_intent = getIntent();

        int id = gotten_intent.getIntExtra(Utils.INTENT_KEY_STUDENT_ID, 0);
        String name = gotten_intent.getStringExtra(Utils.INTENT_KEY_STUDENT_NAME);
        String surname = gotten_intent.getStringExtra(Utils.INTENT_KEY_STUDENT_SURNAME);
        String className = gotten_intent.getStringExtra(Utils.INTENT_KEY_STUDENT_CLASS_NAME);
        int avg = gotten_intent.getIntExtra(Utils.INTENT_KEY_STUDENT_AVERAGE, 0);

        tv_id = findViewById(R.id.tv_id);
        tv_name = findViewById(R.id.tv_name);
        tv_surname = findViewById(R.id.tv_surname);
        tv_class = findViewById(R.id.tv_class);
        tv_average = findViewById(R.id.tv_average);
        tv_subject = findViewById(R.id.tv_subject);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        tv_id.setText("Id: " + id);
        tv_name.setText("Name: " + name);
        tv_surname.setText("Surname: " + surname);
        tv_class.setText("Class: " + className);
        tv_average.setText("Average mark: " + avg);

        btn_update.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Update_Student_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_SHOW_ID, false);

            intent = Utils.defaultIntentToGetStudents(intent, gotten_intent);

            intent.putExtra(Utils.INTENT_KEY_STUDENT_ID, id);
            intent.putExtra(Utils.INTENT_KEY_STUDENT_NAME, name);
            intent.putExtra(Utils.INTENT_KEY_STUDENT_SURNAME, surname);
            intent.putExtra(Utils.INTENT_KEY_STUDENT_CLASS_NAME, className);
            intent.putExtra(Utils.INTENT_KEY_STUDENT_AVERAGE, avg);

            startActivity(intent);
        });

        btn_delete.setOnClickListener(view -> {
            Utils.deleteStudent(id, db);

            Intent intent = new Intent(getApplicationContext(), Get_Students_Screen.class);
            intent = Utils.defaultIntentToGetStudents(intent, gotten_intent);

            startActivity(intent);
        });
    }
}