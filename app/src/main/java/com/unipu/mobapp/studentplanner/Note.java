package com.unipu.mobapp.studentplanner;

public class Note {

        String noteTitle;
        String noteDate;
        String noteDesc;
        String keyNote;

    // Mandatory empty constructor for use of FirebaseUI
    public Note() {}

    public Note(String noteTitle, String noteDesc, String noteDate, String keyNote){
        this.noteTitle = noteTitle;
        this.noteDate = noteDate;
        this.noteDesc = noteDesc;
        this.keyNote = keyNote;
    }

    //Getter and setter method

    public String getKeyNote(){
        return keyNote;
    }

    public void setKeyNote(String keyNote){
        this.keyNote = keyNote;
    }

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