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

    public StudentAdapter(ArrayList<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student tmp = students.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.row_item, null);

        TextView name = convertView.findViewById(R.id.tv_name);
        TextView st_class = convertView.findViewById(R.id.tv_class);
        TextView avg = convertView.findViewById(R.id.tv_avg);

        name.setText("Name: " + tmp.getName() + " " + tmp.getSurname());
        st_class.setText("Class: " + tmp.getSt_class());
        avg.setText("Average: " + tmp.getAvg());

        return convertView;
    }
}
