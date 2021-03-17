package com.unipu.mobapp.studentplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    Button btnAddNew;

    ArrayList<Course> list;

    private RecyclerView courseView;
    CourseAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        /*textView = findViewById(R.id.titlepage);
        textView = findViewById(R.id.subtitlepage);
        textView = findViewById(R.id.endpage);*/

        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(CoursesActivity.this, CourseDetails.class);
                startActivity(a);
            }
        });

        mbase = FirebaseDatabase.getInstance().getReference().child("Course");

        courseView = findViewById(R.id.theCourses);

        // To display the Recycler view linearly
        courseView.setLayoutManager(new LinearLayoutManager(this));

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



