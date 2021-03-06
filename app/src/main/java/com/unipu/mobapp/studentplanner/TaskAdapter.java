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

import java.util.HashMap;


public class TaskAdapter extends FirebaseRecyclerAdapter<Task, TaskAdapter.taskViewholder> {

    private final String data;
    private final HashMap<String, Integer> finishedTasks;

    public TaskAdapter(@NonNull FirebaseRecyclerOptions<Task> options, String data, HashMap<String, Integer> finishedTasks) {
        super(options);
        this.data = data;
        this.finishedTasks = finishedTasks;
    }

    @Override
    protected void onBindViewHolder(@NonNull taskViewholder holder, int position, @NonNull Task model) {

        holder.taskName.setText(String.valueOf(model.getTaskName()));
        holder.grade.setText(String.valueOf(model.getGrade()));
        holder.descript.setText(String.valueOf(model.getDescript()));

        //dodano za povlacenje iz baze u edit
        final String taskName = String.valueOf(model.getTaskName());
        final Integer grade = Integer.valueOf(model.getGrade());
        final String taskType = String.valueOf(model.getTaskType());
        final String finished = String.valueOf(model.getFinished());
        final String descript = String.valueOf(model.getDescript());
        final String editDate = String.valueOf(model.getEditDate());
        final String getKeyTask = String.valueOf(model.getKeytask());

        final Integer finGrade = Integer.valueOf(finishedTasks.get("finGrade"));
        final Integer finExams = Integer.valueOf(finishedTasks.get("finExams"));
        final Integer finHomework = Integer.valueOf(finishedTasks.get("finHomework"));
        final Integer finActivities = Integer.valueOf(finishedTasks.get("finActivities"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (v.getContext(), TaskEdit.class);

                //dodano za povlacenje iz baze u edit
                intent.putExtra("taskName", String.valueOf(taskName));
                intent.putExtra("grade", grade);
                intent.putExtra("taskType", String.valueOf(taskType));
                intent.putExtra("finished", String.valueOf(finished));
                intent.putExtra("descript", String.valueOf(descript));
                intent.putExtra("editDate", String.valueOf(editDate));
                intent.putExtra("keytask", String.valueOf(getKeyTask));
                intent.putExtra("courseID", String.valueOf(data));

                intent.putExtra("finGrade", finGrade);
                intent.putExtra("finExams", finExams);
                intent.putExtra("finHomework", finHomework);
                intent.putExtra("finActivities", finActivities);
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
        TextView taskName, grade, taskType, descript, editDate;

        public taskViewholder(@NonNull View itemView)
        {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            taskName = itemView.findViewById(R.id.taskName);
            grade = itemView.findViewById(R.id.grade);
            taskType = itemView.findViewById(R.id.taskType);
            editDate = itemView.findViewById(R.id.editDate);
            descript = itemView.findViewById(R.id.descript);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent (v.getContext(), TaskEdit.class);
            intent.putExtra("taskName", String.valueOf(taskName));
            intent.putExtra("grade", String.valueOf(grade));
            intent.putExtra("taskType", String.valueOf(taskType));
            intent.putExtra("editDate", String.valueOf(editDate));
            intent.putExtra("descript", String.valueOf(descript));
            v.getContext().startActivity(intent);
        }
    }
}