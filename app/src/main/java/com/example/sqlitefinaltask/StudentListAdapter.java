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

/**
 * Shows how to implement a simple Adapter for a RecyclerView.
 * Demonstrates how to add a click handler for each item in the ViewHolder.
 */
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

        /**
         * Creates a new custom view holder to hold the view to display in
         * the RecyclerView.
         *
         * @param itemView The view in which to display the data.
         * @param adapter The adapter that manages the the data and views
         *                for the RecyclerView.
         */

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

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to
     * represent an item.
     *
     * This new ViewHolder should be constructed with a new View that can
     * represent the items of the given type. You can either create a new View
     * manually or inflate it from an XML layout file.
     *
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder(ViewHolder, int, List). Since it will be reused to
     * display different items in the data set, it is a good idea to cache
     * references to sub views of the View to avoid unnecessary findViewById()
     * calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after
     *                 it is bound to an adapter position.
     * @param viewType The view type of the new View. @return A new ViewHolder
     *                 that holds a View of the given view type.
     */
    @Override
    public StudentListAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View view = layoutInflater.inflate(R.layout.row_list_student_recycler_view, parent, false);

        return new StudentViewHolder(view, this);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the ViewHolder.itemView to
     * reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent
     *                 the contents of the item at the given position in the
     *                 data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(StudentListAdapter.StudentViewHolder holder, int position) {
        // Retrieve the data for that position.
        Student tmp = students.get(position);

        // Add the data to the view holder.
        holder.tv_name.setText(tmp.getName());
        holder.tv_surname.setText(tmp.getSurname());
        holder.tv_class.setText(tmp.getClassName());
        holder.tv_average.setText(""+tmp.getAvg());

        Log.d("recyclerview", tmp.getName() + " | " + tmp.getSurname() + " | " + tmp.getClassName() + " | " + tmp.getAvg());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return students.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}

