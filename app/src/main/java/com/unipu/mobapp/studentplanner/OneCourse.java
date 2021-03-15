package com.unipu.mobapp.studentplanner;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OneCourse {

    String titleOfCourse;
    String numberOfColloquium;

    public OneCourse() {
    }

    public OneCourse(String titleOfCourse, String numberOfColloquium) {
        this.titleOfCourse = titleOfCourse;
        this.numberOfColloquium = numberOfColloquium;
    }

    public String getTitleOfCourse() {
        return titleOfCourse;
    }

    public void setTitleOfCourse(String titleCourse) {
        this.titleOfCourse = titleCourse;
    }

    public String getnumberOfColloquium() {
        return numberOfColloquium;
    }

    public void setnumberOfColloquium(String numberOfColloquium) {
        this.numberOfColloquium = numberOfColloquium;
    }

}

