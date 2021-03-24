package com.unipu.mobapp.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditCourseActivity extends AppCompatActivity {

    EditText editTitle, editColloquium;
    Button btnEditSave, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        editTitle = findViewById(R.id.editTitle);
        editColloquium = findViewById(R.id.editColloquium);

        btnEditSave = findViewById(R.id.btnEditSave);
        btnDelete = findViewById(R.id.btnDelete);

        //get a value
        editTitle.setText(getIntent().getStringExtra("courseName"));
        editColloquium.setText(getIntent().getStringExtra("examNum"));
}
}
