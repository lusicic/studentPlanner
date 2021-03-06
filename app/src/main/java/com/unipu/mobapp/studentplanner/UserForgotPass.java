package com.unipu.mobapp.studentplanner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UserForgotPass extends AppCompatActivity {

    EditText userEmail;
    Button userPass;
    FirebaseAuth mFA;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        userEmail = findViewById(R.id.TextEmailAddress);
        userPass = findViewById(R.id.btnForgotPass);

        mFA = FirebaseAuth.getInstance();

        userPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFA.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserForgotPass.this,
                                    "Password send to you", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(UserForgotPass.this,
                                    task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
