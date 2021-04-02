package com.unipu.mobapp.studentplanner;

public class Course {

    String courseName;
    Long examNum;
    String numActivity;
    String numHomework;
    String numGrade;
    String keyCourse;

    public Course() {
    }

    public Course(String courseName, Long examNum, String keyCourse, String numActivity, String numHomework, String numGrade) {
        this.courseName = courseName;
        this.examNum = examNum;
        this.keyCourse = keyCourse;
        this.numActivity = numActivity;
        this.numGrade = numGrade;
        this.numHomework = numHomework;
    }

    public String getKeyCourse() {
        return keyCourse;
    }

    public String getNumActivity(){
        return numActivity;
    }

    public void setNumActivity(String numActivity){
        this.numActivity = numActivity;
    }

    public String getNumHomework(){
        return numHomework;
    }

    public void setNumHomework(String numHomework){
        this.numHomework = numHomework;
    }

    public void setKeyCourse(String keyCourse) {
        this.keyCourse = keyCourse;
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

    public void setGrade(String numGrade){this.numGrade = numGrade;}

    public String getGrade(){return numGrade;}
}