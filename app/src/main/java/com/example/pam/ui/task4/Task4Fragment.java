package com.example.pam.ui.task4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pam.databinding.FragmentTask4Binding;

public class Task4Fragment extends Fragment {

    private FragmentTask4Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Task4ViewModel galleryViewModel =
                new ViewModelProvider(this).get(Task4ViewModel.class);

        binding = FragmentTask4Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTask4;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}