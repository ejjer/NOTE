package com.example.note.domain;

import android.content.Context;

import com.example.note.R;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotesRepository implements NotesRepository {

    private static NotesRepository INSTANCE;

    public static NotesRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new InMemoryNotesRepository(context);
        }
        return INSTANCE;
    }

    private Context context;

    private InMemoryNotesRepository(Context context) {
        this.context = context;
    }

    @Override
    public List<Note> getAll() {
        ArrayList<Note> result = new ArrayList<>();

        result.add(new Note(context.getString(R.string.note1)));
        result.add(new Note(context.getString(R.string.note2)));
        result.add(new Note(context.getString(R.string.note3)));
        result.add(new Note(context.getString(R.string.note4)));

        return result;
    }

    @Override
    public void add(Note note) {

    }
}
