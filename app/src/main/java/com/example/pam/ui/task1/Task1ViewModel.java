package com.example.pam.ui.task1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Task1ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Task1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is task1 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}