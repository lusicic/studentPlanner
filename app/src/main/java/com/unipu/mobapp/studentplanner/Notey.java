package com.unipu.mobapp.studentplanner;

public class Notey {
    // Variable to store data corresponding
    // to firstname keyword in database
    private String noteTitle;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String noteDate;

    // Variable to store data corresponding
    // to age keyword in database
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