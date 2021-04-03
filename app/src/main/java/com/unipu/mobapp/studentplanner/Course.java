package com.unipu.mobapp.studentplanner;

public class Course {

    String courseName;
    Long examNum;
    String numActivity;
    String numHomework;
    String numGrade;
    String keyCourse;

    Integer finExams;
    Integer finHomework;
    Integer finActivities;

    public Course() {
    }

    public Course(String courseName, Long examNum, String numActivity, String numHomework, String numGrade, String keyCourse, Integer finExams, Integer finHomework, Integer finActivities) {
        this.courseName = courseName;
        this.examNum = examNum;
        this.numActivity = numActivity;
        this.numHomework = numHomework;
        this.numGrade = numGrade;
        this.keyCourse = keyCourse;
        this.finExams = finExams;
        this.finHomework = finHomework;
        this.finActivities = finActivities;
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

    public String getNumGrade() {
        return numGrade;
    }

    public void setNumGrade(String numGrade) {
        this.numGrade = numGrade;
    }

    public Integer getFinExams() {
        return finExams;
    }

    public void setFinExams(Integer finExams) {
        this.finExams = finExams;
    }

    public Integer getFinHomework() {
        return finHomework;
    }

    public void setFinHomework(Integer finHomework) {
        this.finHomework = finHomework;
    }

    public Integer getFinActivities() {
        return finActivities;
    }

    public void setFinActivities(Integer finActivities) {
        this.finActivities = finActivities;
    }
}