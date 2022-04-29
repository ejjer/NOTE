package com.example.note.ui;

import com.example.note.R;
import com.example.note.domain.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private OnNoteClicked noteClicked;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM,  HH:mm", Locale.getDefault());
    private List<Note> data = new ArrayList<>();

    public void setData(Collection<Note> notes) {
        data.addAll(notes);
    }

    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        NotesViewHolder holder = new NotesViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Note note = data.get(position);

        holder.title.setText(note.getTitle());
        holder.massage.setText(note.getMassage());
        holder.date.setText(simpleDateFormat.format(note.getCreatedAt()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    interface OnNoteClicked{
        void onNoteClicked(Note note);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView massage;
        TextView date;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            massage = itemView.findViewById(R.id.massage);
            date = itemView.findViewById(R.id.date);

            itemView.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(noteClicked!=null){

                        int clickedPosition=getAdapterPosition();

                    noteClicked.onNoteClicked(data.get(clickedPosition));
                    }

                }
            });
        }
    }

}
