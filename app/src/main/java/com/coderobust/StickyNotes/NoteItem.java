package com.coderobust.StickyNotes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NoteItem {
    @PrimaryKey(autoGenerate = true)
    int id;
    String title;
    String createdAt;
    String updatedAt;
    String description;

    public NoteItem() {
    }

    public NoteItem(String title, String createdAt, String updatedAt, String description) {
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
