package com.example.note.ui;


import android.os.Bundle;

import com.example.note.domain.*;
import com.example.note.di.*;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.note.R;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class NotesListFragment extends Fragment {

    private Note selectedNote;

    private int selectedPosition;

    private NotesAdapter adapter;


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


        adapter = new NotesAdapter(this);
        adapter.setNoteClicked(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Note note) {
                Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoteLongClicked(Note note, int position) {
                selectedNote = note;
                selectedPosition = position;

            }
        });


        notesList.setAdapter(adapter);


        getParentFragmentManager()
                .setFragmentResultListener(AddNoteBottomSheetFragment.ADD_KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(AddNoteBottomSheetFragment.ARG_NOTE);

                        int index = adapter.addNote(note);
                        adapter.notifyItemInserted(index);
                        notesList.smoothScrollToPosition(index);

                    }
                });

        getParentFragmentManager()
                .setFragmentResultListener(AddNoteBottomSheetFragment.ADD_KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(AddNoteBottomSheetFragment.ARG_NOTE);

                        adapter.replaceNote(note, selectedNote);

                        adapter.notifyDataSetChanged(selectedPosition);

                    }
                });


        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddNoteBottomSheetFragment().addInstance()
                        .show(getParentFragmentManager(), "AddNoteBottomSheetFragment");


            }
        });

        ProgressBar progressBar = view.findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);


        Dependencies.NOTES_REPOSITORY.getAll(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> data) {
                adapter.setData(data);

                adapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable exception) {
                progressBar.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_notes_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_delete:

                Dependencies.NOTES_REPOSITORY.removeNote(selectedNote, new Callback<Void>() {
                    @Override
                    public void onSuccess(Void data) {

                        adapter.removeNote(selectedNote);

                        adapter.notifyItemRemoved(selectedPosition);

                    }

                    @Override
                    public void onError(Throwable exception) {

                    }
                });


                Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.action_edit:
                new AddNoteBottomSheetFragment().editInstance(selectedNote)
                        .show(getParentFragmentManager(), "AddNoteBottomSheetFragment");
                Toast.makeText(requireContext(), "Edit", Toast.LENGTH_SHORT).show();

                return true;

        }
        return super.onContextItemSelected(item);
    }
}