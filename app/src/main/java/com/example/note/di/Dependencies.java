package com.example.note.di;

import com.example.note.domain.*;

public class Dependencies {

    public static final NotesRepository NOTES_REPOSITORY = new InMemoryNotesRepository();
}
