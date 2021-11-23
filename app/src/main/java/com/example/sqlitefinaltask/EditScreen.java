package com.example.sqlitefinaltask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditScreen extends AppCompatActivity {
    EditText name, surname, st_class, avg;
    Button btn;
    TextView title;
    SQLiteDatabase db;
    int id = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        MenuItem item = menu.findItem(R.id.delete);

        if(id == -1 ) item.setVisible(false);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);

        db = openOrCreateDatabase(Utils.DB_name, MODE_PRIVATE, null);
        name = findViewById(R.id.et_name);
        surname = findViewById(R.id.et_surname);
        st_class = findViewById(R.id.et_class);
        avg = findViewById(R.id.et_avg);
        btn = findViewById(R.id.button);
        title = findViewById(R.id.title);

        Intent i = getIntent();
        id = i.getIntExtra("id", -1);
        invalidateOptionsMenu();
        if(id == -1){
            title.setText("Add Student");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Student student = new Student(
                            name.getText().toString(),
                            surname.getText().toString(),
                            st_class.getText().toString(),
                            Integer.parseInt(avg.getText().toString())
                    );

                    Utils.addStudent(student, db);

                    startActivity(new Intent(EditScreen.this, TableActivity.class));
                }
            });
        }

        else{
            Student student = Utils.getStudent(id, db);
            name.setText(student.getName());
            surname.setText(student.getSurname());
            st_class.setText(student.getSt_class());
            avg.setText(String.valueOf(student.getAvg()));

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Student new_st = new Student(
                            id,
                            name.getText().toString(),
                            surname.getText().toString(),
                            st_class.getText().toString(),
                            Integer.parseInt(avg.getText().toString())
                    );

                    Utils.updateStudent(new_st, db);
                    startActivity(new Intent(EditScreen.this, TableActivity.class));
                }
            });

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if(item_id == R.id.delete){
            Utils.deleteStudent(id, db);
            System.out.println(id);
            startActivity(new Intent(EditScreen.this, TableActivity.class));
        }

        return true;
    }
}