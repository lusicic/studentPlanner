package com.unipu.mobapp.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

public class Notey {

        String noteTitle;
        String noteDate;
        String noteDesc;
        String key;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public Notey() {}

    public Notey(String noteTitle, String noteDesc, String noteDate, String key){
        this.noteTitle = noteTitle;
        this.noteDate = noteDate;
        this.noteDesc = noteDesc;
        this.key = key;
    }

    public String getKey(){
        return key;
    }
    public void setKey(String key){
        this.key = key;
    }

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