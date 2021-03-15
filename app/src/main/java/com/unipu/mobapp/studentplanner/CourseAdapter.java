package com.unipu.mobapp.studentplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter  extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{

    Context context;
    ArrayList<OneCourse> oneCourse;

    public CourseAdapter(Context c, ArrayList<OneCourse> p) {
        context = c;
        oneCourse = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.one_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.titleOfCourse.setText(oneCourse.get(i).getTitleOfCourse());
        myViewHolder.numberOfColloquium.setText(oneCourse.get(i).getnumberOfColloquium());

        final String getnumberOfColloquium = oneCourse.get(i).getTitleOfCourse();
        final String getTitleOfCourse = oneCourse.get(i).getnumberOfColloquium();

        /*myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
        return oneCourse.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleOfCourse, numberOfColloquium;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOfCourse = (TextView) itemView.findViewById(R.id.titleOfCourse);
            numberOfColloquium = (TextView) itemView.findViewById(R.id.numberOfColloquium);
        }
    }
}
