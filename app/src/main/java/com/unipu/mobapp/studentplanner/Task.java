package com.unipu.mobapp.studentplanner;

public class Task {
    String taskName;
    Integer grade;
    String taskType;
    String finished;
    String descript;
    String keytask;
    String editDate;

    //trebao bi ga hvatati u recycler kao Course.java ili Note.java
    public Task(){

    }

    public Task(String taskName, Integer grade, String taskType, String finished, String descript, String keytask, String editDate) {
        this.taskName = taskName;
        this.grade = grade;
        this.taskType = taskType;
        this.finished = finished;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
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
