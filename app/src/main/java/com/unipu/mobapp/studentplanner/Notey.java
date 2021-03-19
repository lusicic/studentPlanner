package com.unipu.mobapp.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

public class Notey {

    private String noteTitle;
    private String noteDate;
    private String noteDesc;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public Notey() {}

    // Getter and setter method
    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }
}