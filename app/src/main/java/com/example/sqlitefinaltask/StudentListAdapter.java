package com.example.sqlitefinaltask;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {
    private final ArrayList<Student> students;
    LayoutInflater layoutInflater;
    Context context;


    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView tv_name;
        public final TextView tv_surname;
        public final TextView tv_class;
        public final TextView tv_average;
        final StudentListAdapter adapter;

        public StudentViewHolder(View itemView, StudentListAdapter adapter) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_surname = itemView.findViewById(R.id.tv_surname);
            tv_class = itemView.findViewById(R.id.tv_class);
            tv_average = itemView.findViewById(R.id.tv_average);

            this.adapter = adapter;
            itemView.setOnClickListener(this);
            tv_name.setOnClickListener(this);
            tv_average.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view == tv_name){
                // Get the position of the item that was clicked.
                int position = getLayoutPosition();
                // Use that to access the affected item in students.
                Student student = students.get(position);
                // Change the student in the students.
                students.get(position).setName("Murad");
                // Notify the adapter, that the data has changed so it can
                // update the RecyclerView to display the data.
                adapter.notifyDataSetChanged();
            }
            else if(view == tv_average){
                // Get the position of the item that was clicked.
                int position = getLayoutPosition();
                // Use that to access the affected item in students.
                Student student = students.get(position);
                // Change the student in the students.
                students.get(position).setAvg(100);
                // Notify the adapter, that the data has changed so it can
                // update the RecyclerView to display the data.
                adapter.notifyDataSetChanged();
            }
            else {
                // Get the position of the item that was clicked.
                int position = getLayoutPosition();
                // Use that to access the affected item in students.
                Student student = students.get(position);
                // Change the student in the students.
                students.get(position).setName("------");
                students.get(position).setSurname("------");
                students.get(position).setClassName("------");
                students.get(position).setAvg(0);
                // Notify the adapter, that the data has changed so it can
                // update the RecyclerView to display the data.
                adapter.notifyDataSetChanged();
            }
        }
    }

    public StudentListAdapter(Context context, ArrayList<Student> students) {
        layoutInflater = LayoutInflater.from(context);
        this.students = students;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_list_student_recycler_view, parent, false);

        return new StudentViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder( StudentViewHolder holder, int position) {
        Student tmp = students.get(position);

        holder.tv_name.setText(tmp.getName());
        holder.tv_surname.setText(tmp.getSurname());
        holder.tv_class.setText(tmp.getClassName());
        holder.tv_average.setText(""+tmp.getAvg());

        Log.d("recyclerview", tmp.getName() + " | " + tmp.getSurname() + " | " + tmp.getClassName() + " | " + tmp.getAvg());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}

