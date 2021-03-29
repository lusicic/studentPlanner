package com.unipu.mobapp.studentplanner;

import java.util.ArrayList;
import java.util.List;

public class Course {

    String courseName;
    Long examNum;
    String numActivity;
    String numHomework;
    String keydoes;
    //Taskk task = new Taskk();

    public Course() {
    }

    public Course(String courseName, Long examNum, String keydoes, String numActivity, String numHomework) {
        this.courseName = courseName;
        this.examNum = examNum;
        this.keydoes = keydoes;
        this.numActivity = numActivity;
        this.numHomework = numHomework;
    }

    public String getKeydoes() {
        return keydoes;
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
   /* public void setTask(Taskk task){this.task = task; }
    public Taskk getTask(){return task;}*/

}