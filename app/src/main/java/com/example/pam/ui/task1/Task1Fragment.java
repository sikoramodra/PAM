package com.example.pam.ui.task1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pam.databinding.FragmentTask1Binding;
import com.google.android.material.snackbar.Snackbar;

public class Task1Fragment extends Fragment {

    private FragmentTask1Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Task1ViewModel task1ViewModel =
                new ViewModelProvider(this).get(Task1ViewModel.class);

        binding = FragmentTask1Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.ID3.setOnClickListener(v -> {
           if (binding.ID5.isChecked()) {
               if ((binding.ID2.getText()).toString().isEmpty()) {
                   Toast.makeText(root.getContext(), "Nie wpisano tekstu", Toast.LENGTH_SHORT).show();
                   return;
               }
               Toast.makeText(root.getContext(), (binding.ID2.getText()).toString(), Toast.LENGTH_SHORT).show();
           } else {
               if ((binding.ID2.getText()).toString().isEmpty()) {
                   Snackbar.make(root.getRootView(), "Nie wpisano tekstu", Snackbar.LENGTH_SHORT).show();
                   return;
               }
               Snackbar.make(root.getRootView(), (binding.ID2.getText()).toString(), Snackbar.LENGTH_SHORT).show();
           }
        });

        //final TextView textView = binding.textTask1;
        //task1ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}