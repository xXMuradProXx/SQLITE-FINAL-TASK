package com.example.sqlitefinaltask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    ArrayList<Student> students;
    Context context;

    public StudentAdapter(ArrayList<Student> students, Context context){
        this.students = students;
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        Student tmp = students.get(position);
        convertview = LayoutInflater.from(context).inflate(R.layout.row_list_student, null);

        TextView tv_name = convertview.findViewById(R.id.tv_name);
        TextView tv_surname = convertview.findViewById(R.id.tv_surname);
        TextView tv_class = convertview.findViewById(R.id.tv_class);
        TextView tv_average = convertview.findViewById(R.id.tv_average);

        tv_name.setText(tmp.getName());
        tv_surname.setText(tmp.getSurname());
        tv_class.setText(""+tmp.getClassId());
        tv_average.setText(""+tmp.getAvg());

        return convertview;
    }
}
