package com.coderobust.StickyNotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.coderobust.StickyNotes.R;
import com.coderobust.StickyNotes.databinding.ActivityNoteDetailsBinding;
import com.google.gson.Gson;

public class NoteDetailsActivity extends AppCompatActivity {
    ActivityNoteDetailsBinding binding;
    NoteItem noteItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNoteDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteItem =new Gson().fromJson(getIntent().getStringExtra("data"), NoteItem.class);

        if (getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(noteItem.getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        binding.title.setText(noteItem.getTitle());
        binding.created.setText("Created at"+ noteItem.getCreatedAt());
        binding.updated.setText("Updated at: "+ noteItem.getUpdatedAt());
        binding.details.setText(noteItem.getDescription());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}