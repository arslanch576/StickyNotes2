package com.coderobust.StickyNotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.coderobust.StickyNotes.adapter.NoteItemAdapter;
import com.coderobust.StickyNotes.data.NoteItem;
import com.coderobust.StickyNotes.data.room.AppDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NoteItemAdapter adapter;
    List<NoteItem> data = new ArrayList<>();
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new NoteItemAdapter(this, data);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.addFab).setOnClickListener(v -> {
            startActivity(new Intent(this, AddEditNoteItemActivity.class));
        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.home) {
                data.clear();
                data.addAll(AppDatabase.getDatabase(this).noteItemDao().getAll());
                adapter.notifyDataSetChanged();
            } else if (menuItem.getItemId() == R.id.fvt) {
                data.clear();
                data.addAll(AppDatabase.getDatabase(this).noteItemDao().getAllFvt());
                adapter.notifyDataSetChanged();
            }

            return true;
        });

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.recyclebin) {
                startActivity(new Intent(this, RecycleBinActivity.class));
            }
            return true; //return true to close the navigation drawer
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        data.clear();
        if (bottomNavigationView.getSelectedItemId() == R.id.fvt)
            data.addAll(AppDatabase.getDatabase(this).noteItemDao().getAllFvt());
        else
            data.addAll(AppDatabase.getDatabase(this).noteItemDao().getAll());
        adapter.notifyDataSetChanged();
    }
}