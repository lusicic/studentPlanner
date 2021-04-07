package com.unipu.mobapp.studentplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


public class NotesEdit extends AppCompatActivity {

    EditText editTitle, editDate, editDescr;
    Button btnEditSave, btnDelete;
    DatabaseReference databaseReference;
    TextView addDateCalendar;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(NotesEdit.this,
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
        editTitle = findViewById(R.id.editTitle);
        editDescr = findViewById(R.id.editDescr);

        btnEditSave = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.btnCancelNotes);

        final String keyNote = getIntent().getStringExtra("keyNote");
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(uid).child("Data").child("Note").child("Note" + keyNote);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(NotesEdit.this, "Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NotesEdit.this, NotesActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(NotesEdit.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
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
                        snapshot.getRef().child("noteTitle").setValue(editTitle.getText().toString());
                        snapshot.getRef().child("noteDate").setValue(editDate.getText().toString());
                        snapshot.getRef().child("noteDesc").setValue(editDescr.getText().toString());
                        Intent intent = new Intent(NotesEdit.this, NotesActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        //get a value
       editTitle.setText(getIntent().getStringExtra("noteTitle"));
        editDate.setText(getIntent().getStringExtra("noteDate"));
        editDescr.setText(getIntent().getStringExtra("noteDesc"));
    }
}
