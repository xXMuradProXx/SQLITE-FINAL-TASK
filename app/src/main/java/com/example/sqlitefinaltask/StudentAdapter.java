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

        TextView tv_firstname = convertview.findViewById(R.id.tv_firstname);
        TextView tv_lastname = convertview.findViewById(R.id.tv_lastname);
        TextView tv_class = convertview.findViewById(R.id.tv_class);
        TextView tv_avg = convertview.findViewById(R.id.tv_avg);

        tv_firstname.setText(tmp.getFirst_name());
        tv_lastname.setText(tmp.getLast_name());
        tv_class.setText(tmp.getClass_name());
        tv_avg.setText(""+tmp.getAvg());

        return convertview;
    }
}
