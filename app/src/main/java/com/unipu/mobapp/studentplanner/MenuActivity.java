package com.unipu.mobapp.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    private Button logout, btnStat, btnCourses, btnSchedule, btnCalendar, btnNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MenuActivity.this, UserLogin.class));
                overridePendingTransition(R.anim.activity_anim_two, R.anim.activity_anim_two);
            }
        });

        btnStat = (Button) findViewById(R.id.btnStat);
        btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, StatisticActivity.class));
                overridePendingTransition(R.anim.activity_anim, R.anim.activity_anim);
            }
        });

        btnCourses = (Button) findViewById(R.id.btnCourses);
        btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, CoursesMenu.class));
                overridePendingTransition(R.anim.activity_anim, R.anim.activity_anim);
            }
        });

        btnSchedule = (Button) findViewById(R.id.btnSchedule);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ScheduleActivity.class));
                overridePendingTransition(R.anim.activity_anim, R.anim.activity_anim);
            }
        });

        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, CalendarActivity.class));
                overridePendingTransition(R.anim.activity_anim, R.anim.activity_anim);
            }
        });

        btnNotes = (Button) findViewById(R.id.btnNotes);
        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, NotesActivity.class));
                overridePendingTransition(R.anim.activity_anim, R.anim.activity_anim);
            }
        });
    }
}
