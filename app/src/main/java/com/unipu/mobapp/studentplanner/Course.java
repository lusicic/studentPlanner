package com.unipu.mobapp.studentplanner;

public class Course {

    String courseName;
    Long examNum;
    String keydoes;

    public Course() {
    }

    public Course(String courseName, Long examNum, String keydoes) {
        this.courseName = courseName;
        this.examNum = examNum;
        this.keydoes = keydoes;
    }

    public String getKeydoes() {
        return keydoes;
    }

    public void setKeydoes(String keydoes) {
        this.keydoes = keydoes;
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