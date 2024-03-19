package com.coderobust.StickyNotes.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.coderobust.StickyNotes.data.NoteItem;

@Database(entities = {NoteItem.class}, version = 1)
//@TypeConverters(value = {MyTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteItemDao noteItemDao();
    private static volatile AppDatabase INSTANCE;
    
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class, "database_v11")
                            .fallbackToDestructiveMigration()
                            .fallbackToDestructiveMigrationOnDowngrade()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}