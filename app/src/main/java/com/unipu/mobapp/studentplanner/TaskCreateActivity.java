package com.unipu.mobapp.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TaskCreateActivity extends AppCompatActivity {

    //task
    RadioGroup radioGroup;
    RadioButton radioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskcreate);

        radioGroup = findViewById(R.id.radioGroup);
        radioButton = findViewById(R.id.radioButton);


    }

    public void checkButton(View V){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected " + radioButton.getText(), Toast.LENGTH_SHORT).show();

    }


}