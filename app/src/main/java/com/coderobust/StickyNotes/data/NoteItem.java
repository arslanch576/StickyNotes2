package com.coderobust.StickyNotes.data;

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
    boolean isFvt=false;
    boolean isDeleted=false;

    public NoteItem() {
    }

    public NoteItem(String title, String createdAt, String updatedAt, String description) {
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isFvt() {
        return isFvt;
    }

    public void setFvt(boolean fvt) {
        isFvt = fvt;
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
