package com.coderobust.StickyNotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coderobust.StickyNotes.NoteDetailsActivity;
import com.coderobust.StickyNotes.data.room.AppDatabase;
import com.coderobust.StickyNotes.viewHolders.NoteItemViewHolder;
import com.coderobust.StickyNotes.R;
import com.coderobust.StickyNotes.data.NoteItem;
import com.google.gson.Gson;

import java.util.List;

public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemViewHolder> {

    Context context;
    List<NoteItem> data;

    public NoteItemAdapter(Context context, List<NoteItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NoteItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_note,parent,false);
        return new NoteItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteItemViewHolder holder, int position) {
        NoteItem noteItem=data.get(position);
        holder.details.setText(noteItem.getDescription());
        holder.date.setText(noteItem.getCreatedAt());
        holder.title.setText(noteItem.getTitle());
        holder.fvt.setImageResource(noteItem.isFvt()?R.drawable.star_filled:R.drawable.star_empty);

        holder.post.setOnClickListener(v -> {
            Intent intent=new Intent(context, NoteDetailsActivity.class);
            intent.putExtra("data",new Gson().toJson(noteItem));
            context.startActivity(intent);
        });
        holder.fvt.setOnClickListener(v -> {
            noteItem.setFvt(!noteItem.isFvt());
            AppDatabase.getDatabase(context).noteItemDao().update(noteItem);
            holder.fvt.setImageResource(noteItem.isFvt()?R.drawable.star_filled:R.drawable.star_empty);
//
//            if (noteItem.isFvt()){
//                noteItem.setFvt(false);
//                AppDatabase.getDatabase(context).noteItemDao().update(noteItem);
//                holder.fvt.setImageResource(R.drawable.star_empty);
//            }else {
//                noteItem.setFvt(true);
//                AppDatabase.getDatabase(context).noteItemDao().update(noteItem);
//                holder.fvt.setImageResource(R.drawable.star_filled);
//            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
