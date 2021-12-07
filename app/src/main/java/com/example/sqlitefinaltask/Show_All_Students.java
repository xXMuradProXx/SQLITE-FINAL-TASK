package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Show_All_Students extends AppCompatActivity {

    TextView tv_students_list;
    ListView lv_students;
    Button button;

    SQLiteDatabase db;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_students);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        Utils.addDefaultSubjects(db);

        tv_students_list = findViewById(R.id.tv_students_list);
        lv_students = findViewById(R.id.lv_students);
        button = findViewById(R.id.button);



        students = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from tbl_student", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String first_name = cursor.getString(1);
            String last_name = cursor.getString(2);
            String class_name = cursor.getString(3);
            int avg = cursor.getInt(4);

            Student student = new Student(id, first_name, last_name, class_name, avg);
            students.add(student);
        }

        cursor.close();

        studentAdapter = new StudentAdapter(students, Show_All_Students.this);
        lv_students.setAdapter(studentAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Student> sortedList = Utils.sortStudentsBySubject(students, db);

                ArrayList<Integer> ids = new ArrayList<>();
                ArrayList<String> first_names = new ArrayList<>();
                ArrayList<String> last_names = new ArrayList<>();
                ArrayList<String> class_names = new ArrayList<>();
                ArrayList<Integer> avgs = new ArrayList<>();
                ArrayList<String> subjects = new ArrayList<>();

                for (int i=0; i<sortedList.size(); i++){

                    ids.add(sortedList.get(i).getId());
                    first_names.add(sortedList.get(i).getFirst_name());
                    last_names.add(sortedList.get(i).getLast_name());
                    class_names.add(sortedList.get(i).getClass_name());
                    avgs.add(sortedList.get(i).getAvg());
                    subjects.add(sortedList.get(i).getSubject());
                }

                Intent i = new Intent(getApplicationContext(), Sorted_Students.class);
                i.putExtra(Utils.INTENT_KEY_SORTED_STUDENT_IDS, ids);
                i.putExtra(Utils.INTENT_KEY_SORTED_STUDENT_FIRST_NAMES, first_names);
                i.putExtra(Utils.INTENT_KEY_SORTED_STUDENT_LAST_NAMES, last_names);
                i.putExtra(Utils.INTENT_KEY_SORTED_STUDENT_CLASS_NAMES, class_names);
                i.putExtra(Utils.INTENT_KEY_SORTED_STUDENT_AVGS, avgs);
                i.putExtra(Utils.INTENT_KEY_SORTED_STUDENT_SUBJECTS, subjects);

                startActivity(i);
            }
        });

    }

}