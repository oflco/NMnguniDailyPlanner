package com.nmnguni.nmngunidailyplanner.data;

import java.util.Date;

// NotesEntity class.
public class NotesEntity {
    private  int noteId;
    private Date noteDate;
    private  String noteTitle;
    private  String noteContent;

    // Constructor: Default
    public NotesEntity() {
    }

    // Constructor: Parameterized
    public NotesEntity(int noteId, Date noteDate, String noteTitle, String noteContent) {
        this.noteId = noteId;
        this.noteDate = noteDate;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    // toString method necessary to print the contents of this class.
    @Override
    public String toString() {
        return
            "NotesEntity{" +
            "noteId=" + noteId +
            ", noteDate=" + noteDate +
            ", noteTitle='" + noteTitle + '\'' +
            ", noteContent='" + noteContent + '\'' +
            '}';
    }

    // Properties for getters and setters.
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
