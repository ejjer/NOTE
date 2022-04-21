package com.example.note.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
//import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.note.domain.*;
import com.example.note.R;


import java.util.List;


public class NotesListFragment extends Fragment {

    public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
    public static final String SELECTED_NOTE = "SELECTED_NOTE";
    private static final String ARG_NOTE = "ARG_NOTE";


    public static NoteDetailsFragment newInstance(Note note) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);

        NoteDetailsFragment fragment = new NoteDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Note> noteList = InMemoryNotesRepository.getInstance(requireContext()).getAll();

        LinearLayout container = view.findViewById(R.id.container);


        //nen
        Toolbar toolbar = view.findViewById(R.id.calendar_toolbar);

        if (requireActivity() instanceof ToolbarHolder){
            ((ToolbarHolder)requireActivity()).setToolbar(toolbar);
        }

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new NoteCalendarFragment())
                        .addToBackStack("calendar_toolbar")
                        .commit();
            }
        });


        for (Note note : noteList) {
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, container, false);

            itemView.findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, NoteDetailsFragment.newInstance(note))
                            .addToBackStack("details")
                            .commit();

                }
            });

            TextView title = itemView.findViewById(R.id.title);

            title.setText(note.getName());

            container.addView(itemView);

        }
    }
}
