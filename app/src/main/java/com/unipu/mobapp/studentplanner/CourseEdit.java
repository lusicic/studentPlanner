package com.unipu.mobapp.studentplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Random;

public class CourseEdit extends AppCompatActivity {

    EditText editTitle, editColloquium, editActivity, editHome;
    TextView exam, activty, homework, txtGrade;
    Button btnEditSave, btnDelete, btnTaskCreate;
    DatabaseReference reference, referenceNewTask;
    TaskAdapter adapter;

    Integer brojac = new Random().nextInt();
    String keyCourse = Integer.toString(brojac);

    private RecyclerView taskView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        editTitle = findViewById(R.id.editTitle);
        editColloquium = findViewById(R.id.editColloquium);
        editActivity = findViewById(R.id.editActivity);
        editHome = findViewById(R.id.editHome);

        btnEditSave = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.btnCancelNotes);
        exam = (TextView) findViewById(R.id.exam);
        homework = (TextView) findViewById(R.id.homework);
        activty = (TextView) findViewById(R.id.activity);
        txtGrade = (TextView) findViewById(R.id.txtGrade);


        //get a value
        editTitle.setText(getIntent().getStringExtra("courseName"));
        editColloquium.setText(getIntent().getStringExtra("examNum"));
        editHome.setText(getIntent().getStringExtra("numHomework"));
        editActivity.setText(getIntent().getStringExtra("numActivity"));

        final String CourseKey = getIntent().getStringExtra("keyCourse");

        Integer numFinExams = getIntent().getIntExtra("finExams", 0);
        exam.setText(numFinExams.toString());
        Integer numFinHomework = getIntent().getIntExtra("finHomework", 0);
        homework.setText(numFinHomework.toString());
        Integer numFinActivities = getIntent().getIntExtra("finActivities", 0);
        activty.setText(numFinActivities.toString());

        Integer finGrade = getIntent().getIntExtra("finGrade", 0);
        txtGrade.setText(finGrade.toString());

        HashMap<String, Integer> finishedTasks = new HashMap<String, Integer>();
        finishedTasks.put("finExams", numFinExams);
        finishedTasks.put("finHomework", numFinHomework);
        finishedTasks.put("finActivities", numFinActivities);
        finishedTasks.put("finGrade", finGrade);

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data")
                .child("Course").child("Course" + CourseKey);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CourseEdit.this, "Deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(CourseEdit.this, CoursesMenu.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(CourseEdit.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btnEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("courseName").setValue(editTitle.getText().toString());
                        dataSnapshot.getRef().child("examNum").setValue(Long.parseLong(String.valueOf(editColloquium.getText())));
                        dataSnapshot.getRef().child("numHomework").setValue(editHome.getText().toString());
                        dataSnapshot.getRef().child("numActivity").setValue(editActivity.getText().toString());
                        dataSnapshot.getRef().child("keyCourse").setValue(CourseKey);
                        Intent i = new Intent(CourseEdit.this, CoursesMenu.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        btnTaskCreate = (Button) findViewById(R.id.btnTaskCreate);
        btnTaskCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newTask = new Intent(CourseEdit.this, TaskCreate.class);
                newTask.putExtra("keyCourse", CourseKey);
                startActivity(newTask);
                overridePendingTransition(R.anim.activity_anim, R.anim.activity_anim);
            }
        });

        referenceNewTask = FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("Data").child("Course").child("Course" + CourseKey).child("Task");
        taskView = findViewById(R.id.theTasks);

        // To display the Recycler view linearly
        taskView.setLayoutManager(
                new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Task> options
                = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(referenceNewTask, Task.class)
                .build();
        adapter = new TaskAdapter(options, CourseKey, finishedTasks);
        taskView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}
