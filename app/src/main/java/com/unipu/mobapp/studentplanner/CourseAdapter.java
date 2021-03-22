package com.unipu.mobapp.studentplanner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    class courseViewholder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView courseName, examNum;
        public courseViewholder(@NonNull View itemView)
        {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            courseName = itemView.findViewById(R.id.courseName);
            examNum = itemView.findViewById(R.id.examNum);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent (v.getContext(), CourseDetails.class);
            v.getContext().startActivity(intent);
        }
    }
}