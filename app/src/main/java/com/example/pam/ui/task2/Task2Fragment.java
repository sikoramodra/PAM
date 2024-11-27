package com.example.pam.ui.task2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pam.R;
import com.example.pam.databinding.FragmentTask2Binding;

import java.util.Arrays;
import java.util.List;

public class Task2Fragment extends Fragment {
    private FragmentTask2Binding binding;
    private int index = 0;
    private final List<Task2Imgs> arr = Arrays.asList(
            new Task2Imgs(R.drawable.sikoramodra, "Maecenas semper imperdiet sapien, eget tempor orci sodales nec. In in accumsan ante. Nunc sodales, quam et aliquet bibendum, quam neque mattis purus, eget luctus risus tortor eu nunc."),
            new Task2Imgs(R.drawable.arch, "Cras dolor massa, finibus eget magna vel, faucibus dapibus tortor. Suspendisse potenti. Duis urna mi, egestas in dolor eget, ultricies imperdiet enim. Nam egestas sem eget ullamcorper rutrum."),
            new Task2Imgs(R.drawable.tux, "Maecenas semper imperdiet sapien, eget tempor orci sodales nec. In in accumsan ante. Nunc sodales, quam et aliquet bibendum, quam neque mattis purus, eget luctus risus tortor eu nunc."),
            new Task2Imgs(R.drawable.sikoramodra, "Maecenas semper imperdiet sapien, eget tempor orci sodales nec. In in accumsan ante. Nunc sodales, quam et aliquet bibendum, quam neque mattis purus, eget luctus risus tortor eu nunc."),
            new Task2Imgs(R.drawable.sikoramodra, "Maecenas semper imperdiet sapien, eget tempor orci sodales nec. In in accumsan ante. Nunc sodales, quam et aliquet bibendum, quam neque mattis purus, eget luctus risus tortor eu nunc."),
            new Task2Imgs(R.drawable.sikoramodra, "Maecenas semper imperdiet sapien, eget tempor orci sodales nec. In in accumsan ante. Nunc sodales, quam et aliquet bibendum, quam neque mattis purus, eget luctus risus tortor eu nunc."),
            new Task2Imgs(R.drawable.sikoramodra, "Maecenas semper imperdiet sapien, eget tempor orci sodales nec. In in accumsan ante. Nunc sodales, quam et aliquet bibendum, quam neque mattis purus, eget luctus risus tortor eu nunc."));

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTask2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.buttonGalleryLeft.setOnClickListener(v -> {
            updateGallery(-1);
        });

        binding.buttonGalleryRight.setOnClickListener(v -> {
            updateGallery(+1);
        });

        return root;
    }

    private void updateGallery(int direction) {
        int newIndex = index + direction;

        binding.buttonGalleryLeft.setEnabled(newIndex > 0);
        binding.buttonGalleryRight.setEnabled(newIndex < arr.size() - 1);

        index = newIndex;

        binding.imageViewGallery.setImageResource(arr.get(newIndex).img);
        binding.textViewGallery.setText(arr.get(newIndex).desc);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}