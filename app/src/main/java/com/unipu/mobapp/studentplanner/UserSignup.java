package com.unipu.mobapp.studentplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserSignup extends AppCompatActivity implements View.OnClickListener{

    private TextView signup, banner;
    private EditText editTextEmail, editTextPassword, editUserName, editUserAge, editEducation;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.txtBanner);
        banner.setOnClickListener(this);

        signup = (Button) findViewById(R.id.btnSignup);
        signup.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.txtEmail);
        editTextPassword = (EditText) findViewById(R.id.txtPassword);
        editEducation =  (EditText) findViewById(R.id.txtFaculty);
        editUserAge = (EditText) findViewById(R.id.txtUserAge);
        editUserName = (EditText) findViewById(R.id.txtUserName);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtBanner:
                startActivity(new Intent(this, UserLogin.class));
                overridePendingTransition(R.anim.activity_anim, R.anim.activity_anim);
                break;

            case R.id.btnSignup:
                progressBar.setVisibility(View.VISIBLE);
                signUp();
                overridePendingTransition(R.anim.activity_anim, R.anim.activity_anim);
                break;
        }
    }

    private void signUp() {
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String userName = editUserName.getText().toString().trim();
        String userFaculty = editEducation.getText().toString().trim();
        String userAge = editUserAge.getText().toString().trim();


        if(userName.isEmpty()){
            editUserName.setError("User name is required!");
            editUserName.requestFocus();
            return;
        }

        if (userFaculty.isEmpty()){
            editEducation.setError("User education is required!");
            editEducation.requestFocus();
            return;
        }

        if (userAge.isEmpty()){
            editUserAge.setError("User age is required!");
            editUserAge.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Not valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Min length is 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(UserSignup.this, "User has been successfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    else{
                                        Toast.makeText(UserSignup.this, "Sign up failed! Try again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(UserSignup.this, "Sign up failed! Try again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
