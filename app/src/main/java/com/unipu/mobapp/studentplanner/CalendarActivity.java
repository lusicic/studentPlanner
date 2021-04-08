package com.unipu.mobapp.studentplanner;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.applandeo.materialcalendarview.EventDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.applandeo.materialcalendarview.CalendarView;

import java.util.Date;
import java.util.List;

import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        List<EventDay> events = new ArrayList<>();

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser usery = auth.getCurrentUser();
        final String uid = usery.getUid();

        final List<String> courseIDs = new ArrayList<String>();
        final List<String> taskNames = new ArrayList<String>();

        DatabaseReference mBase, reference;

        mBase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data")
                .child("Course");

        mBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot listSnapshot: dataSnapshot.getChildren()) {
                    String courseID = listSnapshot.child("keyCourse").getValue(String.class);
                    courseIDs.add(courseID);

                    for (int i = 0; i < courseIDs.size(); i++) {
                        String taskName = listSnapshot.child("Course"+String.valueOf(courseIDs.get(i))).getValue(String.class);
                        taskNames.add(taskName);
                    }
                }

                Log.d("list", courseIDs.toString());
                Log.d("listy", taskNames.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /*reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Data")
                .child("Course").child("Course1046928906").child("courseName"); //child("Course" + courseID);
        Log.i("reference", String.valueOf(reference));

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Course> kolegiji = new ArrayList<>(); //treba dobiti iz baze
                for (DataSnapshot ds : snapshot.getChildren()){
                    Course course = ds.getValue(Course.class);
                    kolegiji.add(course);
                }
                    //Course lastitem = kolegiji.get(kolegiji.size()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addListenerForSingleValueEvent(eventListener);*/

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.sample_icon));
        CalendarView calendarView = (CalendarView) findViewById(R.id.viewCalendar);
        calendarView.setEvents(events);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
            }
        });
    }
};
