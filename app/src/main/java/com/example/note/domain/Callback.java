package com.example.note.domain;

public interface Callback<T> {

    void onSuccess(T data);

    void onError(Throwable exception);

}
