package com.coderobust.StickyNotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coderobust.StickyNotes.data.NoteItem;
import com.coderobust.StickyNotes.data.room.AppDatabase;
import com.coderobust.StickyNotes.databinding.ActivityAddEditNoteItemBinding;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEditNoteItemActivity extends AppCompatActivity {

    ActivityAddEditNoteItemBinding binding;
    NoteItem noteItem = new NoteItem();
    boolean isForEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditNoteItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Edit notes");
        }

        if (getIntent().hasExtra("data")) {
            noteItem = new Gson().fromJson(getIntent().getStringExtra("data"), NoteItem.class);
            isForEdit = true;

            binding.title.setText(noteItem.getTitle());
            binding.details.setText(noteItem.getDescription());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            //save note to database
            if (binding.title.getText().toString().isEmpty() && binding.details.getText().toString().isEmpty()) {
                Toast.makeText(this, "Both title and details could not be empty", Toast.LENGTH_SHORT).show();
                return true;
            }
            noteItem.setTitle(binding.title.getText().toString());
            noteItem.setDescription(binding.details.getText().toString());
            if (!isForEdit)
                noteItem.setCreatedAt(new SimpleDateFormat("dd-MM-yyyy HH:mm a").format(new Date()));
            noteItem.setUpdatedAt(new SimpleDateFormat("dd-MM-yyyy HH:mm a").format(new Date()));
            //save
            AppDatabase.getDatabase(this).noteItemDao().insertOrReplace(noteItem);
            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}