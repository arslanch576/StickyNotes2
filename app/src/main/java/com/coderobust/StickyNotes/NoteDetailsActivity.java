package com.coderobust.StickyNotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.coderobust.StickyNotes.data.NoteItem;
import com.coderobust.StickyNotes.data.room.AppDatabase;
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

        binding.edit.setOnClickListener(v -> {
            startActivity(new Intent(this,AddEditNoteItemActivity.class).putExtra("data",new Gson().toJson(noteItem)));
            finish();
        });
        binding.delete.setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Sure to delete?");
            alert.setMessage("Are you sure you want to delete?");
            alert.setCancelable(false);
            alert.setPositiveButton("Yes, delete", (dialog, which) -> {
                AppDatabase.getDatabase(this).noteItemDao().delete(noteItem);
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                finish();
            });
            alert.setNegativeButton(android.R.string.no, (dialog, which) -> {
                dialog.dismiss();
            });
            alert.show();
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}