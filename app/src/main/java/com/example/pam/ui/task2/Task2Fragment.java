package com.example.pam.ui.task2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pam.databinding.FragmentTask2Binding;

public class Task2Fragment extends Fragment {

    private FragmentTask2Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Task2ViewModel task2ViewModel =
                new ViewModelProvider(this).get(Task2ViewModel.class);

        binding = FragmentTask2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTask2;
        task2ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}