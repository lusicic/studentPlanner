package com.unipu.mobapp.studentplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CoursesActivity extends AppCompatActivity {

    Button btnAddNew;

    private RecyclerView courseView;
    CourseAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        btnAddNew = findViewById(R.id.btnAdd);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(CoursesActivity.this, CourseCreate.class);
                startActivity(a);
            }
        });

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        mbase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data").child("Course");
        courseView = findViewById(R.id.theCourses);

        // To display the Recycler view linearly
        courseView.setLayoutManager(
                new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Course> options
                = new FirebaseRecyclerOptions.Builder<Course>()
                .setQuery(mbase, Course.class)
                .build();

        adapter = new CourseAdapter(options);
        courseView.setAdapter(adapter);
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



