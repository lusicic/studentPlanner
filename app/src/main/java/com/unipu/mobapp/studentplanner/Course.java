package com.unipu.mobapp.studentplanner;

public class Course {

    String courseName;
    Long examNum;

    public Course() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getExamNum() {
        return examNum;
    }

    public void setExamNum(Long examNum) {
        this.examNum = examNum;
    }
}