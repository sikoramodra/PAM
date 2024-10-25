package com.example.pam.ui.task4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Task4ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Task4ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is task4 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}