package com.coderobust.StickyNotes;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coderobust.StickyNotes.R;
import com.coderobust.StickyNotes.adapter.NoteItemAdapter;
import com.coderobust.StickyNotes.data.NoteItem;
import com.coderobust.StickyNotes.data.room.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecycleBinActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NoteItemAdapter adapter;
    List<NoteItem> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_bin);

        getSupportActionBar().setTitle("Recycle bin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data.addAll(AppDatabase.getDatabase(this).noteItemDao().getAllDeleted());
        adapter = new NoteItemAdapter(this, data);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}