package com.example.pam.ui.task3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Task3ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Task3ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is task3 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}