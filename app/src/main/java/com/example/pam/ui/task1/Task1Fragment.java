package com.example.pam.ui.task1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pam.databinding.FragmentTask1Binding;

public class Task1Fragment extends Fragment {

    private FragmentTask1Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Task1ViewModel task1ViewModel =
                new ViewModelProvider(this).get(Task1ViewModel.class);

        binding = FragmentTask1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTask1;
        task1ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}