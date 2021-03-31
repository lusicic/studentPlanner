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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

public class EditTaskActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rbtnTaskType;
    Button btnEditSave, btnDelete;

    TextView textView, addDateCalendar;
    EditText taskName, grade, descript, editDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    DatabaseReference databaseReference;
    Integer brojac = new Random().nextInt();
    String keytask = Integer.toString(brojac);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        addDateCalendar = (TextView) findViewById(R.id.titleDate);
        addDateCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTaskActivity.this,
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

        editDate = findViewById(R.id.editDate);
        taskName = findViewById(R.id.taskName);
        grade = findViewById(R.id.grade);
        descript = findViewById(R.id.descript);

        btnEditSave = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.btnCancelNotes);

        final String key = getIntent().getStringExtra("key");
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data").child("Notey").child("Notey" + key);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditTaskActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditTaskActivity.this, NotesActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(EditTaskActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        btnEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("taskName").setValue(taskName.getText().toString());
                        snapshot.getRef().child("editDate").setValue(editDate.getText().toString());
                        snapshot.getRef().child("grade").setValue(grade.getText().toString());
                        snapshot.getRef().child("descript").setValue(descript.getText().toString());
                        Intent intent = new Intent(EditTaskActivity.this, EditCourseActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        //get a value
        taskName.setText(getIntent().getStringExtra("taskName"));
        editDate.setText(getIntent().getStringExtra("noteDate"));
        grade.setText(getIntent().getStringExtra("grade"));
        descript.setText(getIntent().getStringExtra("descript"));
    }
}
