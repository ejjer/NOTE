package com.example.note.ui;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.note.R;
import com.example.note.domain.Note;

import java.util.Calendar;


public class NoteCalendarFragment extends Fragment {

    private static final String ARG_CALENDAR = "ARG_CALENDAR";
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        public static NoteCalendarFragment newInstance(Calendar calendar) {

            Bundle args = new Bundle();
            args.putParcelable(ARG_CALENDAR, calendar);

            NoteCalendarFragment fragment = new NoteCalendarFragment();
            fragment.setArguments(args);
            return fragment;
        }


        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .popBackStack();
            }
        });


    }
}
