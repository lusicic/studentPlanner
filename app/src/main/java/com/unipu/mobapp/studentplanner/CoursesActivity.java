package com.unipu.mobapp.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    Button btnAddNew;
    TextView textView;

    DatabaseReference reference;
    RecyclerView ourcoursee;
    ArrayList<OneCourse> list;
    CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        textView = findViewById(R.id.titlepage);
        textView = findViewById(R.id.subtitlepage);
        textView = findViewById(R.id.endpage);

        btnAddNew = findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(CoursesActivity.this,CourseDetails.class);
                startActivity(a);
            }
        });

        //working with data
        ourcoursee = findViewById(R.id.ourcoursee);
        ourcoursee.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<OneCourse>();

        //get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("CourseApp");
        courseAdapter = new CourseAdapter(CoursesActivity.this, list);
        ourcoursee.setAdapter(courseAdapter);
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    OneCourse p = dataSnapshot1.getValue(OneCourse.class);
                    list.add(p);
                }
                courseAdapter = new CourseAdapter(CoursesActivity.this, list);
                ourcoursee.setAdapter(courseAdapter);
                courseAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }

        });
    }
}



