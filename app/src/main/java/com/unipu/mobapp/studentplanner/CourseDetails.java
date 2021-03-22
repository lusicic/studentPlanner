package com.unipu.mobapp.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.util.Random;


public class CourseDetails extends AppCompatActivity {
    TextView textView;
    EditText titleOfCourse, numberOfColloquium;
    Button btnSave, btnCancel;

    DatabaseReference reference;
    Integer brojac = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedetails);
        textView = findViewById(R.id.titlepage);
        textView = findViewById(R.id.addtitle);
        textView = findViewById(R.id.addNumber);

        numberOfColloquium = (EditText) findViewById(R.id.examNum);
        titleOfCourse = (EditText) findViewById(R.id.courseName);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("Course").child("Course" + brojac);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("courseName").setValue(titleOfCourse.getText().toString());
                        dataSnapshot.getRef().child("examNum").setValue(Long.parseLong(String.valueOf(numberOfColloquium.getText())));

                        Intent a = new Intent(CourseDetails.this,CoursesActivity.class);
                        startActivity(a);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
    }
};