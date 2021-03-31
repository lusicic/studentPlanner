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

import java.util.Random;

public class CourseEdit extends AppCompatActivity {

    EditText editTitle, editColloquium, editActivity, editHome;
    Button btnEditSave, btnDelete, btnTaskCreate;
    DatabaseReference reference, mBase;
    TaskAdapter adapter;

    Integer brojac = new Random().nextInt();
    String keydoes = Integer.toString(brojac);

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

        //get a value
        editTitle.setText(getIntent().getStringExtra("courseName"));
        editColloquium.setText(getIntent().getStringExtra("examNum"));
        editHome.setText(getIntent().getStringExtra("numHomework"));
        editActivity.setText(getIntent().getStringExtra("numActivity"));

        final String keykeyDoes = getIntent().getStringExtra("keydoes");


        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data").child("Course").child("Course" + keykeyDoes);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CourseEdit.this, "Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CourseEdit.this, CoursesMenu.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(CourseEdit.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
                        dataSnapshot.getRef().child("keydoes").setValue(keykeyDoes);
                        Intent a = new Intent(CourseEdit.this, CoursesMenu.class);
                        //CourseEdit.super.onBackPressed();
                        startActivity(a);
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
                newTask.putExtra("keydoes", keykeyDoes);
                startActivity(newTask);
                overridePendingTransition(R.anim.activity_anim, R.anim.activity_anim);
            }
        });

        mBase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data").child("Course").child("Course" + keykeyDoes).child("Task");
        taskView = findViewById(R.id.theTasks);

        // To display the Recycler view linearly

        taskView.setLayoutManager(
                new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Task> options
                = new FirebaseRecyclerOptions.Builder<Task>()
                .setQuery(mBase, Task.class)
                .build();
        adapter = new TaskAdapter(options, keykeyDoes);
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
