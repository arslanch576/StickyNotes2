package com.coderobust.StickyNotes.viewHolders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coderobust.StickyNotes.R;

public class NoteItemViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView details;
    public TextView date;
    public LinearLayout post;
    public NoteItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.title);
        details =itemView.findViewById(R.id.details);
        date=itemView.findViewById(R.id.date);
        post=itemView.findViewById(R.id.post);
    }
}
