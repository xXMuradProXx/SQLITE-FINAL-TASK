package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Sorted_Students extends AppCompatActivity {

    TextView tv_sorted_list;
    ListView lv_sorted;
    SortedStudentsAdapter sortedStudentsAdapter;
    ArrayList<Student> sortedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_students);

        tv_sorted_list = findViewById(R.id.tv_sorted_list);
        lv_sorted = findViewById(R.id.lv_sorted);

        sortedList = new ArrayList<>();

        Intent intent = getIntent();

        ArrayList<Integer> ids = intent.getIntegerArrayListExtra(Utils.INTENT_KEY_SORTED_STUDENT_IDS);
        ArrayList<String> first_names = intent.getStringArrayListExtra(Utils.INTENT_KEY_SORTED_STUDENT_FIRST_NAMES);
        ArrayList<String> last_names = intent.getStringArrayListExtra(Utils.INTENT_KEY_SORTED_STUDENT_LAST_NAMES);
        ArrayList<String> class_names = intent.getStringArrayListExtra(Utils.INTENT_KEY_SORTED_STUDENT_CLASS_NAMES);
        ArrayList<Integer> avgs = intent.getIntegerArrayListExtra(Utils.INTENT_KEY_SORTED_STUDENT_AVGS);
        ArrayList<String> subjects = intent.getStringArrayListExtra(Utils.INTENT_KEY_SORTED_STUDENT_SUBJECTS);

        for (int i=0; i<ids.size(); i++){
            int id = ids.get(i);
            String first_name = first_names.get(i);
            String last_name = last_names.get(i);
            String class_name = class_names.get(i);
            int avg = avgs.get(i);
            String subject = subjects.get(i);

            Student student = new Student(id, first_name, last_name, class_name, avg, subject);
            sortedList.add(student);
        }

        sortedStudentsAdapter = new SortedStudentsAdapter(sortedList, getApplicationContext());
        lv_sorted.setAdapter(sortedStudentsAdapter);


    }
}