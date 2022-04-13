package com.example.note.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;




public class NotesListFragment extends Fragment {

    public static final  String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
    public static final  String SELECTED_NOTE = "SELECTED_NOTE";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Note> noteList=InMemoryNotesRepository.getInstance(requireContext()).getAll();

        LinearLayout container = view.findViewById(R.id.container);

        for(Note note: notes){
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note,container,false);

            itemView.findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(SELECTED_NOTE,note);
                        getParentFragmentManager()
                                .setFragmentResult(NOTES_CLICKED_KEY,bundle);
                    }else {
                        NoteDetailActivity.show(requireContext(), note);
                    }

                }
            });

            TextView title = itemView.findViewById(R.id.title);

            title.setText(note.getName());

            container.addView(itemView);

        }
    }
}
