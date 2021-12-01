package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class Get_Students_Screen extends AppCompatActivity {
    TextView tv_students_list;

    Switch switch_by_subject;

    ListView lv_students;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    ListView lv_students_by_subject;
    SubjectAdapter subjectAdapter;

    SQLiteDatabase db;

    Intent gotten_intent;
    Intent intent;
    Dialog choiceDialog;
    boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_students_screen);

        getSupportActionBar().hide();

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        gotten_intent = getIntent();

        tv_students_list = findViewById(R.id.tv_students_list);

        lv_students = findViewById(R.id.lv_students);
        lv_students_by_subject = findViewById(R.id.lv_students_by_subjects);
        lv_students_by_subject.setVisibility(View.INVISIBLE);

        students = getStudentsByIntent(gotten_intent);

        studentAdapter = new StudentAdapter(students, Get_Students_Screen.this);
        lv_students.setAdapter(studentAdapter);
        lv_students.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = students.get(i);
                createChoiceDialog(student);
            }
        });

        Utils.resetSubjectTable(db);
        Utils.addDefaultSubjectsv2(db);
        ArrayList<Student> added = Utils.addSubjectToStudents(students, db);
        ArrayList<Student> sorted = Utils.sort(added);
        Log.d("check", "The size of added " + sorted.size());
        for(int i=0; i < sorted.size(); i++){
            Log.d("check", sorted.get(i).getId() + " | "
                    + sorted.get(i).getName() + " | "
                    + sorted.get(i).getSurname() + " | "
                    + sorted.get(i).getClassName() + " | "
                    + sorted.get(i).getAvg() + " | "
                    + sorted.get(i).getSubject());
        }
        subjectAdapter = new SubjectAdapter(sorted, Get_Students_Screen.this);
        lv_students_by_subject.setAdapter(subjectAdapter);
        lv_students_by_subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = students.get(i);
                createChoiceDialog(student);
            }
        });

        switch_by_subject = findViewById(R.id.switch_by_subject);
        switch_by_subject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    lv_students.setVisibility(View.INVISIBLE);
                    lv_students_by_subject.setVisibility(View.VISIBLE);

                    checked = true;
                }
                else {
                    lv_students_by_subject.setVisibility(View.INVISIBLE);
                    lv_students.setVisibility(View.VISIBLE);

                    checked = false;
                }

            }
        });
        checked = gotten_intent.getBooleanExtra(Utils.INTENT_KEY_CHECKED, false);
        switch_by_subject.setChecked(checked);

    }

    public void createChoiceDialog(Student student) {
        choiceDialog = new Dialog(this);
        choiceDialog.setContentView(R.layout.choise_dialog);
        choiceDialog.setCancelable(true);

        TextView tv_update = choiceDialog.findViewById(R.id.tv_update);
        tv_update.setOnClickListener(view -> {
            intent = new Intent(this, Update_Student_Screen.class);
            intent.putExtra(Utils.INTENT_KEY_SHOW_ID, false);

            intent.putExtra(Utils.INTENT_KEY_STUDENT_ID, student.getId());
            intent.putExtra(Utils.INTENT_KEY_STUDENT_NAME, student.getName());
            intent.putExtra(Utils.INTENT_KEY_STUDENT_SURNAME, student.getSurname());
            intent.putExtra(Utils.INTENT_KEY_STUDENT_CLASS_NAME, student.getClassName());
            intent.putExtra(Utils.INTENT_KEY_STUDENT_AVERAGE, student.getAvg());

            intent = Utils.defaultIntentToGetStudents(intent, gotten_intent);

            startActivity(intent);
            choiceDialog.dismiss();
        });

        TextView tv_delete = choiceDialog.findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(view -> {
            Utils.deleteStudent(student.getId(), db);

            intent = new Intent(getApplicationContext(), Get_Students_Screen.class);
            intent = Utils.defaultIntentToGetStudents(intent, gotten_intent);

            startActivity(intent);
            choiceDialog.dismiss();
        });

        choiceDialog.show();
    }

    public ArrayList<Student> getStudentsByIntent(Intent gotten_intent){
        String table = gotten_intent.getStringExtra(Utils.INTENT_KEY_GET_STUDENTS);

        if(table.equals(Utils.INTENT_KEY_GET_STUDENTS_BY_NAME)){
            String name = gotten_intent.getStringExtra(Utils.INTENT_KEY_GET_STUDENTS_BY_NAME_STUDENT_NAME);
            students = Utils.getStudentsByName(name, db);
            tv_students_list.setText("List of students with name " + name);
        }
        else if(table.equals(Utils.INTENT_KEY_GET_STUDENTS_BY_CLASS)) {
            String className = gotten_intent.getStringExtra(Utils.INTENT_KEY_GET_STUDENTS_BY_CLASS_STUDENT_CLASS_NAME);
            students = Utils.getStudentsByClass(className, db);
            tv_students_list.setText("List of students with class " + className);
        }
        else if(table.equals(Utils.INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE)){
            int avg = gotten_intent.getIntExtra(Utils.INTENT_KEY_GET_STUDENTS_BY_HIGHER_AVERAGE_STUDENT_AVERAGE, 0);
            students = Utils.getStudentsByHigherAvg(avg, db);
            tv_students_list.setText("List of students with average mark higher than " + avg);
        }
        else if(table.equals(Utils.INTENT_KEY_GET_STUDENTS)){
            students = new ArrayList<>();

            Cursor cursor = db.rawQuery("select * from tbl_student", null);
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                String className = cursor.getString(3);
                int avg = cursor.getInt(4);

                System.out.println(id);
                System.out.println(name);
                System.out.println(surname);
                System.out.println(className);
                System.out.println(avg);

                Student student = new Student(id, name, surname, className, avg);
                students.add(student);
            }
        }

        return students;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}