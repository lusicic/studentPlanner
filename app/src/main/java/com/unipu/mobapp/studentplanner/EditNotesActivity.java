package com.unipu.mobapp.studentplanner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class EditNotesActivity extends AppCompatActivity {

    EditText editTitle, editDate, editDescr;
    Button btnEditSave, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        editTitle = findViewById(R.id.editTitle);
        editDate = findViewById(R.id.editDate);
        editDescr = findViewById(R.id.editDescr);

        btnEditSave = findViewById(R.id.btnEditSave);
        btnDelete = findViewById(R.id.btnDelete);

        //get a value
        editTitle.setText(getIntent().getStringExtra("editTitle"));
        editDate.setText(getIntent().getStringExtra("editDate"));
        editDescr.setText(getIntent().getStringExtra("editDescr"));
    }
}
