package com.example.pam.ui.task3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pam.databinding.FragmentTask3Binding;

public class Task3Fragment extends Fragment {

    private FragmentTask3Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Task3ViewModel task3ViewModel =
                new ViewModelProvider(this).get(Task3ViewModel.class);

        binding = FragmentTask3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTask3;
        task3ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}