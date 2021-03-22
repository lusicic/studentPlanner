package com.unipu.mobapp.studentplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class CourseAdapter extends FirebaseRecyclerAdapter<Course, CourseAdapter.courseViewholder> {

    public CourseAdapter(@NonNull FirebaseRecyclerOptions<Course> options)
    {
        super(options);
    }

   @Override
    protected void
    onBindViewHolder(@NonNull courseViewholder holder, int position, @NonNull Course model)
    {

        holder.courseName.setText(model.getCourseName());
        holder.examNum.setText(String.valueOf(model.getExamNum()));

    }


    @NonNull
    @Override
    public courseViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course, parent, false);
        return new courseViewholder(view);
    }

    class courseViewholder
            extends RecyclerView.ViewHolder {
        TextView courseName, examNum;
        public courseViewholder(@NonNull View itemView)
        {
            super(itemView);

            courseName = itemView.findViewById(R.id.courseName);
            examNum = itemView.findViewById(R.id.examNum);
        }
    }
}