package com.example.note.domain;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.note.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InMemoryNotesRepository implements NotesRepository {

    private ArrayList<Note> data = new ArrayList<>();

    private Executor executor = Executors.newSingleThreadExecutor();

    private Handler handler = new Handler(Looper.getMainLooper());

    public InMemoryNotesRepository() {

    }


    @Override
    public void getAll(Callback<List<Note>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(data);
                    }
                });

            }
        });


    }

    @Override
    public void addNote(String title, String massage, Callback<Note> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                Note note = new Note(UUID.randomUUID().toString(), title, massage, new Date());

                data.add(note);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(note);
                    }
                });
            }
        });

    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }


                data.remove(note);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(null);
                    }
                });
            }
        });

    }

    @Override
    public void updateNote(Note note, String title, String massage, Callback<Note> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                Note newNote = new Note(note.getId(), title, massage, note.getCreatedAt());

                int index = data.indexOf(note);


                data.set(index, newNote);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(newNote);
                    }
                });
            }
        });

    }
}


