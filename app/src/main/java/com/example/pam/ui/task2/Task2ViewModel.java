package com.example.pam.ui.task2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Task2ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Task2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is task2 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}