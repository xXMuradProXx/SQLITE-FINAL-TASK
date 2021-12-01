package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete_Student_Screen extends AppCompatActivity {
    SQLiteDatabase db;

    EditText et_id;
    Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student_screen);

        getSupportActionBar().hide();
        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        et_id = findViewById(R.id.et_id);

        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(et_id.getText().toString());

                try {
                    Utils.deleteStudent(id, db);
                    Toast.makeText(Delete_Student_Screen.this, "The Student was successfully deleted from database", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Delete_Student_Screen.this, "The Student not found", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}