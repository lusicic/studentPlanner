package com.unipu.mobapp.studentplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder>{

    Context context;
    ArrayList<Course> course;

    public CourseAdapter(Context c, ArrayList<Course> p) {
        context = c;
        course = p;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseHolder(LayoutInflater.from(context).inflate(R.layout.course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder courseHolder, int i) {
        courseHolder.courseName.setText(course.get(i).getCourseName());
        courseHolder.examNum.setText(course.get(i).getExamNum());

        final String getCourseName = course.get(i).getCourseName();
        final String getExamNum = course.get(i).getExamNum();

        /*courseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context, EditTaskTable.class);
                aa.putExtra("course", getTitleOfCourse);
                aa.putExtra("number", getnumberOfColloquium);
                context.startActivity(aa);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return course.size();
    }

    public class CourseHolder extends RecyclerView.ViewHolder{
        public TextView courseName, examNum;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            courseName = (TextView) itemView.findViewById(R.id.courseName);
            examNum = (TextView) itemView.findViewById(R.id.examNum);
        }
    }
}
