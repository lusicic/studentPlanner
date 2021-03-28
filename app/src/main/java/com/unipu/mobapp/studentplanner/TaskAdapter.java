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


public class TaskAdapter extends FirebaseRecyclerAdapter<Taskk, TaskAdapter.taskViewholder> {

    public TaskAdapter(@NonNull FirebaseRecyclerOptions<Taskk> options) { super(options); }




    @Override
    protected void onBindViewHolder(@NonNull taskViewholder holder, int position, @NonNull Taskk model) {


        holder.taskName.setText(String.valueOf(model.getTaskName()));
        holder.grade.setText(String.valueOf(model.getGrade()));
        holder.descript.setText(String.valueOf(model.getDescript()));

        //dodano za povlacenje iz baze u edit
        final String taskName = String.valueOf(model.getTaskName());
        final String grade = String.valueOf(model.getGrade());
        final String descript = String.valueOf(model.getDescript());
        final String getKeytask = String.valueOf(model.getKeytask());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (v.getContext(), TaskCreateActivity.class);

                //dodano za povlacenje iz baze u edit
                intent.putExtra("taskName", String.valueOf(taskName));
                intent.putExtra("grade", String.valueOf(grade));
                intent.putExtra("descript", String.valueOf(descript));
                intent.putExtra("keytask", String.valueOf(getKeytask));
                v.getContext().startActivity(intent);
            }

        });
    }

    @NonNull
    @Override
    public taskViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task, parent, false);
        return new taskViewholder(view);
    }



    class taskViewholder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView taskName, grade, keytask, descript;
        public taskViewholder(@NonNull View itemView)
        {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            taskName = itemView.findViewById(R.id.taskName);
            grade = itemView.findViewById(R.id.grade);
            descript = itemView.findViewById(R.id.descript);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent (v.getContext(), TaskCreateActivity.class);
            intent.putExtra("taskName", String.valueOf(taskName));
            intent.putExtra("grade", String.valueOf(grade));
            intent.putExtra("descript", String.valueOf(descript));
            v.getContext().startActivity(intent);
        }
    }


}