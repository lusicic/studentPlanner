package com.unipu.mobapp.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


public class CourseCreate extends AppCompatActivity {
    TextView titlePage, addTitle, addNumber, addActivity, addHomework;
    EditText titleOfCourse, numberOfColloquium, numberActivity, numberHomework;
    Button btnSave, btnCancel;

    DatabaseReference reference;
    Integer brojac = new Random().nextInt();
    String keyCourse = Integer.toString(brojac);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursecreate);
        titlePage = findViewById(R.id.titlepage);
        addTitle = findViewById(R.id.addtitle);
        addNumber = findViewById(R.id.addNumber);
        addActivity = findViewById(R.id.addActivity);
        addHomework = findViewById(R.id.addHomework);

        numberOfColloquium = (EditText) findViewById(R.id.examNum);
        titleOfCourse = (EditText) findViewById(R.id.courseName);
        numberActivity = (EditText) findViewById(R.id.numActivity);
        numberHomework = (EditText) findViewById(R.id.numHomework);

        btnSave = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.buttonDelete);

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data").child("Course").child("Course" + brojac);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("courseName").setValue(titleOfCourse.getText().toString());
                        dataSnapshot.getRef().child("examNum").setValue(Long.parseLong(String.valueOf(numberOfColloquium.getText())));
                        dataSnapshot.getRef().child("numActivity").setValue(numberActivity.getText().toString());
                        dataSnapshot.getRef().child("numHomework").setValue(numberHomework.getText().toString());
                        dataSnapshot.getRef().child("finGrade").setValue(0);

                        dataSnapshot.getRef().child("finExams").setValue(0);
                        dataSnapshot.getRef().child("finHomework").setValue(0);
                        dataSnapshot.getRef().child("finActivities").setValue(0);

                        dataSnapshot.getRef().child("keyCourse").setValue(keyCourse);
                        Intent a = new Intent(CourseCreate.this, CoursesMenu.class);
                        CourseCreate.super.onBackPressed();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseCreate.super.onBackPressed();
            }
        });
    }
};