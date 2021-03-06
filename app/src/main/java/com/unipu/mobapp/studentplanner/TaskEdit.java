package com.unipu.mobapp.studentplanner;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class TaskEdit extends AppCompatActivity {


    EditText taskName, grade, descript, editDate;
    Button btnEdit, buttonDelete, btnDoneTask;
    DatabaseReference mBase, mBase2;
    TextView addDateCalendar;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    Integer brojac = new Random().nextInt();
    String keytask = Integer.toString(brojac);

    List<String> spinnerArray =  new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);


        addDateCalendar = (TextView) findViewById(R.id.addDate);
        addDateCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(TaskEdit.this,
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

        taskName = findViewById(R.id.taskName);
        grade = findViewById(R.id.grade);
        descript = findViewById(R.id.descript);
        editDate =  findViewById(R.id.editDate);

        btnEdit = findViewById(R.id.btnEdit);
        buttonDelete = findViewById(R.id.buttonDelete);
        btnDoneTask = findViewById(R.id.btnDoneTask);

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                editDate.setText(date);
            }
        };

        //get a value
        taskName.setText(getIntent().getStringExtra("taskName"));

        final Integer taskGrade = Integer.valueOf(getIntent().getIntExtra("grade", 0));
        grade.setText(taskGrade.toString());

        editDate.setText(getIntent().getStringExtra("editDate"));
        descript.setText(getIntent().getStringExtra("descript"));


        final String keykey = getIntent().getStringExtra("keytask");
        final String courseID = getIntent().getStringExtra("courseID");

        String taskType = String.valueOf(getIntent().getStringExtra("taskType"));
        String statusOfTask = String.valueOf(getIntent().getStringExtra("finished"));

        String finishedN = "in progress";
        String finishedY = "done";

        spinnerArray.add("exam");
        spinnerArray.add("homework");
        spinnerArray.add("activity");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnerTaskType = (Spinner) findViewById(R.id.spinnerTaskType);
        spinnerTaskType.setAdapter(adapter);

        int spinnerPosition = adapter.getPosition(taskType);

        spinnerTaskType.setSelection(spinnerPosition);

        final Integer finGrade = Integer.valueOf(getIntent().getIntExtra("finGrade", 0));

        final Integer finExams = Integer.valueOf(getIntent().getIntExtra("finExams", 0));
        final Integer finHomework = Integer.valueOf(getIntent().getIntExtra("finHomework", 0));
        final Integer finActivities = Integer.valueOf(getIntent().getIntExtra("finActivities", 0));


        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        mBase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data")
                .child("Course").child("Course" + courseID).child("Task").child("Task" + keykey);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBase.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(TaskEdit.this, "Deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(TaskEdit.this, CoursesMenu.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(TaskEdit.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("taskName").setValue(taskName.getText().toString());
                        dataSnapshot.getRef().child("descript").setValue(descript.getText().toString());
                        dataSnapshot.getRef().child("grade").setValue(Integer.parseInt(String.valueOf(grade.getText())));
                        dataSnapshot.getRef().child("taskType").setValue(String.valueOf(spinnerTaskType.getSelectedItem().toString()));
                        dataSnapshot.getRef().child("editDate").setValue(editDate.getText().toString());
                        dataSnapshot.getRef().child("keytask").setValue(keykey);
                        Intent i = new Intent(TaskEdit.this, CoursesMenu.class);
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

        // dohvatit vrijednosti izvrsenih taskova i inkrementirat

        mBase2 = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data")
                .child("Course").child("Course" + courseID);

        btnDoneTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mBase2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if((taskType.equals("exam")) && (statusOfTask.equals(finishedN))) {
                            dataSnapshot.getRef().child("finExams").setValue(finExams + 1);
                            dataSnapshot.getRef().child("finGrade").setValue(finGrade + taskGrade);
                            dataSnapshot.getRef().child("Task").child("Task" + keykey).child("finished").setValue(finishedY);
                        }
                        else if((taskType.equals("homework")) && (statusOfTask.equals(finishedN))) {
                            dataSnapshot.getRef().child("finHomework").setValue(finHomework + 1);
                            dataSnapshot.getRef().child("finGrade").setValue(finGrade + taskGrade);
                            dataSnapshot.getRef().child("Task").child("Task" + keykey).child("finished").setValue(finishedY);
                        }
                        else if((taskType.equals("activity")) && (statusOfTask.equals(finishedN))) {
                            dataSnapshot.getRef().child("finActivity").setValue(finActivities + 1);
                            dataSnapshot.getRef().child("finGrade").setValue(finGrade + taskGrade);
                            dataSnapshot.getRef().child("Task").child("Task" + keykey).child("finished").setValue(finishedY);
                        }

                        Intent i = new Intent(TaskEdit.this, MenuActivity.class);
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
    }
}
