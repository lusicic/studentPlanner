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


public class NotesCreate extends AppCompatActivity {
    TextView textView;
    EditText editTitle, editDate, editDesc;
    Button btnSaveNotes, btnCancelNotes;

    DatabaseReference reference;
    Integer brojac = new Random().nextInt();
    String key = Integer.toString(brojac);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notescreate);
        textView = findViewById(R.id.titlepage);
        textView = findViewById(R.id.titleTitle);
        textView = findViewById(R.id.titleDate);
        textView = findViewById(R.id.titleDescr);

        editDate = (EditText) findViewById(R.id.editDate);
        editTitle = (EditText) findViewById(R.id.editTitle);
        editDesc = (EditText) findViewById(R.id.editDescr);

        btnSaveNotes = findViewById(R.id.btnCreate);
        btnCancelNotes = findViewById(R.id.btnCancelNotes);

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        btnSaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data").child("Notey").child("Notey" + brojac);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("noteTitle").setValue(editTitle.getText().toString());
                        dataSnapshot.getRef().child("noteDate").setValue(editDate.getText().toString());
                        dataSnapshot.getRef().child("noteDesc").setValue(editDesc.getText().toString());
                        dataSnapshot.getRef().child("key").setValue(key);
                        Intent a = new Intent(NotesCreate.this, NotesActivity.class);
                       // a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       // a.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       // a.putExtra("EXIT", true);
                       // startActivity(a);
                       // finish();
                        NotesCreate.super.onBackPressed();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        btnCancelNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesCreate.super.onBackPressed();
            }
        });
    }
};