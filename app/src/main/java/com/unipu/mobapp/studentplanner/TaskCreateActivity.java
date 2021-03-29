package com.unipu.mobapp.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

public class TaskCreateActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnSave, btnCancel;

    TextView textView;
    EditText taskName, grade, descript;

    DatabaseReference reference;
    Integer brojac = new Random().nextInt();
    String keytask = Integer.toString(brojac);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskcreate);

        textView = findViewById(R.id.titlepage);
        textView = findViewById(R.id.addtitle);
        textView = findViewById(R.id.addNumber);
        textView = findViewById(R.id.tasktype);
        textView = findViewById(R.id.addHomework);

        radioGroup = findViewById(R.id.radioGroup);
        radioButton = findViewById(R.id.radioButton);

        taskName = (EditText) findViewById(R.id.taskName);
        grade = (EditText) findViewById(R.id.grade);
        descript = (EditText) findViewById(R.id.descript);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        taskName.setText(getIntent().getStringExtra("taskName"));
        grade.setText(getIntent().getStringExtra("grade"));
        descript.setText(getIntent().getStringExtra("descript"));

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        final String courseID = getIntent().getExtras().getString("keydoes");

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data").child("Course").child("Course"+courseID).child("Task").child("Task" + brojac);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ovaj dio bi trebao unositi Task unutar postojeceg odabrenog kolegija
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("taskName").setValue(taskName.getText().toString());
                        dataSnapshot.getRef().child("grade").setValue(grade.getText().toString());
                        dataSnapshot.getRef().child("descript").setValue(descript.getText().toString());
                        dataSnapshot.getRef().child("keytask").setValue(keytask);

                        Intent intent = new Intent(TaskCreateActivity.this, EditCourseActivity.class);
                        TaskCreateActivity.super.onBackPressed();
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
                TaskCreateActivity.super.onBackPressed();
            }
        });
    }

    public void checkButton(View V) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}