package com.unipu.mobapp.studentplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

public class TaskCreateActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rbtnTaskType;
    Button btnSave, btnCancel;

    TextView textView, addDateCalendar;
    EditText taskName, grade, descript, editDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    DatabaseReference reference;
    Integer brojac = new Random().nextInt();
    String keytask = Integer.toString(brojac);

    private String taskType = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskcreate);

        textView = findViewById(R.id.titlepage);
        textView = findViewById(R.id.addtitle);
        textView = findViewById(R.id.addNumber);
        textView = findViewById(R.id.tasktype);
        textView = findViewById(R.id.addHomework);
        addDateCalendar = (TextView) findViewById(R.id.addDate);

        addDateCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(TaskCreateActivity.this,
                        android.R.style.Theme_Material_Dialog_NoActionBar,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow();
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                editDate.setText(date);
            }
        };
        radioGroup = findViewById(R.id.radioGroup);
        //radioButton = findViewById(R.id.radioButton);

        taskName = (EditText) findViewById(R.id.taskName);
        grade = (EditText) findViewById(R.id.grade);
        descript = (EditText) findViewById(R.id.descript);
        editDate = (EditText) findViewById(R.id.editDate);

        btnSave = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.buttonDelete);

        taskName.setText(getIntent().getStringExtra("taskName"));
        grade.setText(getIntent().getStringExtra("grade"));
        descript.setText(getIntent().getStringExtra("descript"));
        editDate.setText(getIntent().getStringExtra("editDate"));

        rbtnTaskType = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        taskType = rbtnTaskType.getText().toString();

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
                        dataSnapshot.getRef().child("taskType").setValue(taskType);
                        dataSnapshot.getRef().child("descript").setValue(descript.getText().toString());
                        dataSnapshot.getRef().child("editDate").setValue(editDate.getText().toString());
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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.exam:
                        taskType = "exam";
                        break;
                    case R.id.homework:
                        taskType = "homework";
                        break;
                    case R.id.activity:
                        taskType = "activity";
                        break;
                }
            }
        });
    }
}