package com.coderobust.StickyNotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.coderobust.StickyNotes.databinding.ActivityAddEditNoteItemBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEditNoteItemActivity extends AppCompatActivity {

    ActivityAddEditNoteItemBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddEditNoteItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Edit notes");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_notes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.save){
            //save note to database
            if (binding.title.getText().toString().isEmpty()&&binding.details.getText().toString().isEmpty()){
                Toast.makeText(this, "Both title and details could not be empty", Toast.LENGTH_SHORT).show();
                return true;
            }
            NoteItem noteItem=new NoteItem();
            noteItem.setTitle(binding.title.getText().toString());
            noteItem.setDescription(binding.details.getText().toString());
            noteItem.setCreatedAt(new SimpleDateFormat("dd-MM-yyyy HH:mm a").format(new Date()));
            noteItem.setUpdatedAt(new SimpleDateFormat("dd-MM-yyyy HH:mm a").format(new Date()));
            //save
            AppDatabase.getDatabase(this).noteItemDao().insertOrReplace(noteItem);
            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}