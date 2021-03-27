package com.unipu.mobapp.studentplanner;

public class Task {
    String taskName;
    Long grade;
    String descript;
    String keytask;

    //trebao bi ga hvatati u recycler kao Course.java ili Notey.java
    public Task(){

    }

    public Task(String taskName, Long grade, String descript, String keytask) {
        this.taskName = taskName;
        this.grade = grade;
        this.descript = descript;
        this.keytask = keytask;
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
        return keytask;
    }

    public void setKeytask(String keytask) {
        this.keytask = keytask;
    }
}
