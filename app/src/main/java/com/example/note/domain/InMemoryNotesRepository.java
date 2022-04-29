package com.example.note.domain;

import android.content.Context;

import com.example.note.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InMemoryNotesRepository implements NotesRepository {

    private ArrayList<Note> data = new ArrayList<>();

    public InMemoryNotesRepository(){
        data.add(new Note(UUID.randomUUID().toString(),"Title 1", "massage 1", new Date()));
        data.add(new Note(UUID.randomUUID().toString(),"Title 2", "massage 2", new Date()));
        data.add(new Note(UUID.randomUUID().toString(),"Title 3", "massage 3", new Date()));
        data.add(new Note(UUID.randomUUID().toString(),"Title 4", "massage 4", new Date()));
        data.add(new Note(UUID.randomUUID().toString(),"Title 5", "massage 5", new Date()));
        data.add(new Note(UUID.randomUUID().toString(),"Title 6", "massage 6", new Date()));
        data.add(new Note(UUID.randomUUID().toString(),"Title 7", "massage 7", new Date()));
        data.add(new Note(UUID.randomUUID().toString(),"Title 8", "massage 8", new Date()));
        
        for(int i = 0; i<3000; i++){
            data.add(new Note(UUID.randomUUID().toString(),"Title 8", "massage 8", new Date()));
        }
    }

    @Override
    public List<Note> getAll() {
        return data;
    }
}

