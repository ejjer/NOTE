package com.example.note.ui;


import android.os.Bundle;

import com.example.note.domain.*;
import com.example.note.di.*;
import com.example.note.ui.*;

import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.note.R;
import com.example.note.ui.NotesAdapter;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class NotesListFragment extends Fragment {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM,  HH:mm", Locale.getDefault());

    public NotesListFragment() {
        super(R.layout.fragment_notes_list);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        notesList.setLayoutManager(layoutManager);


        NotesAdapter adapter = new NotesAdapter();
        adapter.setNoteClicked(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        // NotesAdapter adapter = new NotesAdapter();
        notesList.setAdapter(adapter);

        adapter.setData(notes);//notes

        adapter.notifyDataSetChanged();


        // List<Note> notes = Dependencies.NOTES_REPOSITORY.getAll();


    }
}