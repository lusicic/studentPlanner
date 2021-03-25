package com.unipu.mobapp.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class EditNotesActivity extends AppCompatActivity {

    EditText editTitle, editDate, editDescr;
    Button btnEditSave, btnDelete;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        editTitle = findViewById(R.id.editTitle);
        editDate = findViewById(R.id.editDate);
        editDescr = findViewById(R.id.editDescr);

        btnEditSave = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.btnCancelNotes);
        final String key = getIntent().getStringExtra("key");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notey").child("Notey" + key);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditNotesActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditNotesActivity.this, NotesActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(EditNotesActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
                        Intent intent = new Intent(EditNotesActivity.this, NotesActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        //get a value
       editTitle.setText(getIntent().getStringExtra("noteTitle"));
        editDate.setText(getIntent().getStringExtra("noteDate"));
        editDescr.setText(getIntent().getStringExtra("noteDesc"));
    }
}
