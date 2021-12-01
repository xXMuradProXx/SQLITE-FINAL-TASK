package com.example.sqlitefinaltask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassAdapter extends BaseAdapter {
    ArrayList<Class> classes;
    Context context;

    public ClassAdapter(ArrayList<Class> classes, Context context){
        this.classes = classes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return classes.size();
    }

    @Override
    public Class getItem(int position) {
        return classes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        Class tmp = classes.get(position);
        convertview = LayoutInflater.from(context).inflate(R.layout.row_list_class, null);

        TextView tv_name = convertview.findViewById(R.id.tv_name);
        TextView tv_teacher = convertview.findViewById(R.id.tv_teacher);

        tv_name.setText(tmp.getName());
        tv_teacher.setText(tmp.getTeacher());

        return convertview;
    }

}
