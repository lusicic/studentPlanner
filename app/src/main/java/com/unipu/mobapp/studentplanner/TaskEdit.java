package com.unipu.mobapp.studentplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Random;

public class TaskEdit extends AppCompatActivity {

    EditText taskName, grade, descript, editDate;
    Button btnEdit, buttonDelete;
    DatabaseReference mBase;
    TaskAdapter adapter;

    Integer brojac = new Random().nextInt();
    String keytask = Integer.toString(brojac);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        taskName = findViewById(R.id.taskName);
        grade = findViewById(R.id.grade);
        descript = findViewById(R.id.descript);
        editDate = findViewById(R.id.editDate);

        btnEdit = findViewById(R.id.btnEdit);
        buttonDelete = findViewById(R.id.buttonDelete);

        //get a value
        taskName.setText(getIntent().getStringExtra("taskName"));
        grade.setText(getIntent().getStringExtra("grade"));
        editDate.setText(getIntent().getStringExtra("editDate"));
        descript.setText(getIntent().getStringExtra("descript"));

        final String keykey = getIntent().getStringExtra("keytask");
        final String courseID = getIntent().getExtras().getString("keydoes");

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        mBase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data").child("Course").child("Course" + courseID).child("Task").child("Task" + keykey);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBase.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(TaskEdit.this, "Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TaskEdit.this, CoursesMenu.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(TaskEdit.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
                        dataSnapshot.getRef().child("grade").setValue(grade.getText().toString());
                        dataSnapshot.getRef().child("editDate").setValue(editDate.getText().toString());
                        dataSnapshot.getRef().child("keytask").setValue(keykey);
                        Intent a = new Intent(TaskEdit.this, CoursesMenu.class);
                        //CourseEdit.super.onBackPressed();
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
