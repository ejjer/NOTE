package com.example.note.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note {
    private String id;
    private String title;
    private String massage;
    private Date createdAt;


    public Note(String id, String title, String massage, Date createdAt) {
        this.id = id;
        this.title = title;
        this.massage = massage;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMassage() {
        return massage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }


}
