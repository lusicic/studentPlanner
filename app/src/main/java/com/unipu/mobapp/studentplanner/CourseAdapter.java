package com.unipu.mobapp.studentplanner;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class  CourseAdapter extends FirebaseRecyclerAdapter<Course, CourseAdapter.courseViewholder> {

    public CourseAdapter(@NonNull FirebaseRecyclerOptions<Course> options)
    {
        super(options);
    }

   @Override
    protected void
    onBindViewHolder(@NonNull courseViewholder holder,
                     int position, @NonNull Course model)
    {
        holder.courseName.setText(String.valueOf(model.getCourseName()));
        holder.examNum.setText(String.valueOf(model.getExamNum()));

        //dodano za povlacenje iz baze u edit
        final String courseName = String.valueOf(model.getCourseName());
        final String examNum = String.valueOf(model.getExamNum());
        final String numActivity = String.valueOf(model.getNumActivity());
        final String numHomework = String.valueOf(model.getNumHomework());
        final String KeyCourse = String.valueOf(model.getKeyCourse());

        final Integer finGrade = Integer.valueOf(model.getFinGrade());
        final Integer finExams = Integer.valueOf(model.getFinExams());
        final Integer finHomework = Integer.valueOf(model.getFinHomework());
        final Integer finActivities = Integer.valueOf(model.getFinActivities());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (v.getContext(), CourseEdit.class);

                //dodano za povlacenje iz baze u edit
                intent.putExtra("courseName", String.valueOf(courseName));
                intent.putExtra("examNum", String.valueOf(examNum));
                intent.putExtra("numActivity", String.valueOf(numActivity));
                intent.putExtra("numHomework", String.valueOf(numHomework));
                intent.putExtra("keyCourse", String.valueOf(KeyCourse));

                intent.putExtra("finGrade", finGrade);
                intent.putExtra("finExams", finExams);
                intent.putExtra("finHomework", finHomework);
                intent.putExtra("finActivites", finActivities);

                v.getContext().startActivity(intent);
            }

        });
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
        TextView courseName, examNum, numActivity, numHomework;
        public courseViewholder(@NonNull View itemView)
        {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            courseName = itemView.findViewById(R.id.courseName);
            examNum = itemView.findViewById(R.id.examNum);
            numHomework = itemView.findViewById(R.id.numHomework);
            numActivity = itemView.findViewById(R.id.numActivity);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent (v.getContext(), CourseEdit.class);
            intent.putExtra("courseName", String.valueOf(courseName));
            intent.putExtra("examNum", String.valueOf(examNum));
            intent.putExtra("numHomework", String.valueOf(numHomework));
            intent.putExtra("numActivity", String.valueOf(numActivity));
            v.getContext().startActivity(intent);
        }
    }
}