package com.unipu.mobapp.studentplanner;

public class Course {

    String courseName;
    String examNum;

    public Course() {
    }

    public Course(String courseName, String examNum) {
        this.courseName = courseName;
        this.examNum = examNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getExamNum() {
        return examNum;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum;
    }
}

