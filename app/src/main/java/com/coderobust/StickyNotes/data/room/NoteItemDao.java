package com.coderobust.StickyNotes.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.coderobust.StickyNotes.data.NoteItem;

import java.util.List;

@Dao
public interface NoteItemDao {
    @Query("SELECT * FROM NoteItem order by id desc limit 500")
    List<NoteItem> getAll();
    @Query("SELECT * FROM NoteItem limit 500")
    LiveData<List<NoteItem>> liveGetAll();
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOrReplace(NoteItem noteItem);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceAll(List<NoteItem> noteItems);
    
    @Delete
    void delete(NoteItem blogPost);
    
    @Update
    void update(NoteItem blogPost);
    
    @Query("DELETE FROM NoteItem")
    void deleteAll();

}