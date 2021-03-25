package com.unipu.mobapp.studentplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotesActivity extends AppCompatActivity {

    Button btnAdd;

    private RecyclerView recyclerView;
    NoteyAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this, NotesCreate.class);
                startActivity(intent);
            }
        });

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        mbase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data").child("Notey");

        recyclerView = findViewById(R.id.theNotes);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Notey> options
                = new FirebaseRecyclerOptions.Builder<Notey>()
                .setQuery(mbase, Notey.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new NoteyAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}