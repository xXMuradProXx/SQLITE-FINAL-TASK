package com.example.sqlitefinaltask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TeacherAdapter extends BaseAdapter {
    ArrayList<Teacher> teachers;
    Context context;

    public TeacherAdapter(ArrayList<Teacher> teachers, Context context){
        this.teachers = teachers;
        this.context = context;
    }


    public int getCount() {
        return teachers.size();
    }


    public Teacher getItem(int position) {
        return teachers.get(position);
    }


    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertview, ViewGroup viewGroup) {
        Teacher tmp = teachers.get(position);
        convertview = LayoutInflater.from(context).inflate(R.layout.row_list_teacher, null);

        TextView tv_name = convertview.findViewById(R.id.tv_name);
        TextView tv_surname = convertview.findViewById(R.id.tv_surname);
        TextView tv_subject = convertview.findViewById(R.id.tv_subject);

        tv_name.setText(tmp.getName());
        tv_surname.setText(tmp.getSurname());
        tv_subject.setText(tmp.getSubject());

        return convertview;
    }
}
