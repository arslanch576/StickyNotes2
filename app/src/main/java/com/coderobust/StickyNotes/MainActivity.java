package com.coderobust.StickyNotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.coderobust.StickyNotes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NoteItemAdapter adapter;
    List<NoteItem> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter=new NoteItemAdapter(this,data);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.addFab).setOnClickListener(v -> {
            startActivity(new Intent(this,AddEditNoteItemActivity.class));
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        data.clear();
        data.addAll(AppDatabase.getDatabase(this).noteItemDao().getAll());
        adapter.notifyDataSetChanged();
    }
}