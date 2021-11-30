package com.example.sqlitefinaltask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    ListView lv;
    SQLiteDatabase db;
    StudentAdapter adapter;
    FloatingActionButton fab;
    Dialog d;
    EditText  et_filter;
    TextView title;
    Button btn_filter;
    MenuItem filter_class, filter_grade, reset;
    String filter, filter_content;
    ArrayList<Student> students;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.table_menu, menu);
        filter_class = menu.findItem(R.id.f_class);
        filter_grade = menu.findItem(R.id.f_grade);
        reset = menu.findItem(R.id.reset);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        db = openOrCreateDatabase(Utils.DB_name, MODE_PRIVATE, null);
        lv = findViewById(R.id.lv);
        fab = findViewById(R.id.fab);
        Intent i = getIntent();
        filter = i.getStringExtra(Utils.FILTER_KEY);
        filter_content = i.getStringExtra(Utils.FILTER_TEXT_KEY);

        if(filter == null){
            students = Utils.getStudents(db);
        } else if (filter.equals(Utils.BY_CLASS_VALUE)) {
            students = Utils.getClassStudents(filter_content, db);
        }else {
            students = Utils.getHigherAvg(Integer.parseInt(filter_content), db);
        }

        adapter = new StudentAdapter(students, TableActivity.this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int st_id = students.get(position).getId();
                Intent intent = new Intent(TableActivity.this, EditScreen.class);
                intent.putExtra("id", st_id);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TableActivity.this, EditScreen.class));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if(item_id == R.id.f_class){
            createDialog();
            btn_filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tmp = new Intent(TableActivity.this, TableActivity.class);
                    tmp.putExtra(Utils.FILTER_KEY, Utils.BY_CLASS_VALUE);
                    tmp.putExtra(Utils.FILTER_TEXT_KEY, et_filter.getText().toString());
                    startActivity(tmp);
                }
            });
            d.show();

        }

        else if (item_id == R.id.f_grade){
            createDialog();
            title.setText("Enter grade to filter by");
            et_filter.setHint("Enter the minimum grade");
            btn_filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tmp = new Intent(TableActivity.this, TableActivity.class);
                    tmp.putExtra(Utils.FILTER_KEY, Utils.BY_GRADE_VALUE);
                    tmp.putExtra(Utils.FILTER_TEXT_KEY, et_filter.getText().toString());
                    startActivity(tmp);
                }
            });
            d.show();
        }else if (item_id == R.id.reset){
            startActivity(new Intent(TableActivity.this, TableActivity.class));
        }else if(item_id == R.id.reset_students){
            Utils.resetTables(db);
            startActivity(new Intent(TableActivity.this, TableActivity.class));
        }

        return true;
    }


    private void createDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.dialog);
        d.setTitle("Enter filter");
        d.setCancelable(true);
        title = d.findViewById(R.id.d_title);
        et_filter  = d.findViewById(R.id.et_filter);
        btn_filter = d.findViewById(R.id.btn_filter);

    }

    @Override
    public void onBackPressed() {
        if(filter == null){
            startActivity(new Intent(TableActivity.this, MainActivity.class));
        }
        else{
            startActivity(new Intent(TableActivity.this, TableActivity.class));
        }
    }
}