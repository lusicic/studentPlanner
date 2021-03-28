package com.unipu.mobapp.studentplanner;

public class Taskk {
    String taskName;
    Long grade;
    String descript;
    String keydoes;

    //trebao bi ga hvatati u recycler kao Course.java ili Notey.java
    public Taskk(){

    }

    public Taskk(String taskName, Long grade, String descript, String keydoes) {
        this.taskName = taskName;
        this.grade = grade;
        this.descript = descript;
        this.keydoes = keydoes;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getKeytask() {
        return keydoes;
    }

    public void setKeytask(String keytask) {
        this.keydoes = keytask;
    }
}
