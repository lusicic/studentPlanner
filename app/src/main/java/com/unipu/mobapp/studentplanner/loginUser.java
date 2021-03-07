package com.unipu.mobapp.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class








































loginUser extends AppCompatActivity implements View.OnClickListener{

    private TextView txtSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        txtSignup = (TextView) findViewById(R.id.txtSignup);
        txtSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txtSignup:
                startActivity(new Intent(this, signupUser.class));
                break;
        }
    }
}
