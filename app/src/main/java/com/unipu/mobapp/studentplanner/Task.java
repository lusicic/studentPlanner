package com.unipu.mobapp.studentplanner;

public class Task {
    String taskName;
    String grade;
    String taskType;
    String descript;
    String keytask;
    String editDate;

    //trebao bi ga hvatati u recycler kao Course.java ili Note.java
    public Task(){

    }

    public Task(String taskName, String grade, String taskType, String descript, String keytask, String editDate) {
        this.taskName = taskName;
        this.grade = grade;
        this.taskType = taskType;
        this.descript = descript;
        this.keytask = keytask;
        this.editDate = editDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
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

    public void setEditDate(String editDate){this.editDate = editDate;}

    public String getEditDate(){return editDate; }
}
