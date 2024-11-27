package com.example.pam.ui.task3;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pam.databinding.FragmentTask3Binding;

import java.util.ArrayList;

public class Task3Fragment extends Fragment {

    private ListView myList;
    private MyCustomAdapter adapter;
    private TodoListSQLHelper todoListSQLHelper;

    private FragmentTask3Binding binding;
    private ArrayList<String> taskList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTask3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the database helper
        todoListSQLHelper = new TodoListSQLHelper(requireContext());

        // Load tasks from the database
        taskList = loadTasksFromDatabase();

        // Initialize the adapter
        adapter = new MyCustomAdapter(requireContext(), taskList, this::deleteTask);

        // Set the adapter to the ListView
        myList = binding.list;
        myList.setAdapter(adapter);

        // Floating action button for adding a new task
        ImageButton fabImageButton = binding.fabImageButton;
        fabImageButton.setOnClickListener(v -> showAddTaskDialog());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Load tasks from the database into the ArrayList
    private ArrayList<String> loadTasksFromDatabase() {
        ArrayList<String> tasks = new ArrayList<>();
        SQLiteDatabase db = todoListSQLHelper.getReadableDatabase();

        Cursor cursor = db.query(TodoListSQLHelper.TABLE_NAME,
                new String[]{TodoListSQLHelper.COL1_TASK},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            tasks.add(cursor.getString(cursor.getColumnIndexOrThrow(TodoListSQLHelper.COL1_TASK)));
        }

        cursor.close();
        db.close();

        return tasks;
    }

    // Show a dialog to add a new task
    private void showAddTaskDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle("Add a List item.");
        dialogBuilder.setMessage("Describe the item.");

        final EditText todoET = new EditText(requireContext());
        dialogBuilder.setView(todoET);

        dialogBuilder.setPositiveButton("Add Item", (dialog, which) -> {
            String todoTaskInput = todoET.getText().toString();
            if (!todoTaskInput.isEmpty()) {
                addTaskToDatabase(todoTaskInput);
                taskList.add(todoTaskInput);
                adapter.notifyDataSetChanged();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", null);
        dialogBuilder.create().show();
    }

    // Add a task to the database
    private void addTaskToDatabase(String task) {
        SQLiteDatabase db = todoListSQLHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TodoListSQLHelper.COL1_TASK, task);
        db.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }

    // Delete a task
    private void deleteTask(String task) {
        SQLiteDatabase db = todoListSQLHelper.getWritableDatabase();
        db.delete(TodoListSQLHelper.TABLE_NAME,
                TodoListSQLHelper.COL1_TASK + " = ?",
                new String[]{task});
        db.close();

        // Remove the task from the list and refresh the adapter
        taskList.remove(task);
        adapter.notifyDataSetChanged();
    }
}