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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pam.R;
import com.example.pam.databinding.FragmentTask3Binding;

import java.util.ArrayList;

public class Task3Fragment extends Fragment {
    private ListView myList;
    private ListAdapter todoListAdapter;
    private TodoListSQLHelper todoListSQLHelper;

    private FragmentTask3Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTask3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ArrayList<String> list = new ArrayList<>();
        final MyCustomAdapter adapter = new MyCustomAdapter(this.getContext(), list);

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        binding.list,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {

                                    String deleteTodoItemSql = "DELETE FROM " + TodoListSQLHelper.TABLE_NAME +
                                            " WHERE " + TodoListSQLHelper._ID+ " = '" + todoListAdapter.getItemId(position) + "'";

                                    todoListSQLHelper = new TodoListSQLHelper(root.getContext());
                                    SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
                                    sqlDB.execSQL(deleteTodoItemSql);
                                    updateTodoList();

                                }
                            }

                        });


        myList = binding.list;
        ImageButton fabImageButton = binding.fabImageButton;

        fabImageButton.setOnClickListener(v -> {
                list.add("New Item");
                adapter.notifyDataSetChanged();
                AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(root.getContext());
                todoTaskBuilder.setTitle("Add a List item.");
                todoTaskBuilder.setMessage("Describe the item.");
                final EditText todoET = new EditText(root.getContext());
                todoTaskBuilder.setView(todoET);
                todoTaskBuilder.setPositiveButton("Add Item", (dialogInterface, i) -> {
                    String todoTaskInput = todoET.getText().toString();
                    todoListSQLHelper = new TodoListSQLHelper(root.getContext());
                    SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.clear();

                    //write the Todo task input into database table
                    values.put(TodoListSQLHelper.COL1_TASK, todoTaskInput);
                    sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                    //update the Todo task list UI
                    updateTodoList();
                });

                todoTaskBuilder.setNegativeButton("Cancel", null);

                todoTaskBuilder.create().show();
        });

        //show the ListView on the screen
        // The adapter MyCustomAdapter is responsible for maintaining the data backing this list and for producing
        // a view to represent an item in that data set.

        updateTodoList();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateTodoList() {
        todoListSQLHelper = new TodoListSQLHelper(getContext());
        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getReadableDatabase();

        //cursor to read todo task list from database
        Cursor cursor = sqLiteDatabase.query(TodoListSQLHelper.TABLE_NAME,
                new String[]{TodoListSQLHelper._ID, TodoListSQLHelper.COL1_TASK},
                null, null, null, null, null);

        //binds the todo task list with the UI
        todoListAdapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.due,
                cursor,
                new String[]{TodoListSQLHelper.COL1_TASK},
                new int[]{R.id.due_text_view},
                0
        );

        myList.setAdapter(todoListAdapter);
    }

    //closing the todo task item
    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView todoTV = (TextView) v.findViewById(R.id.due_text_view);
        String todoTaskItem = todoTV.getText().toString();

        String deleteTodoItemSql = "DELETE FROM " + TodoListSQLHelper.TABLE_NAME +
                " WHERE " + TodoListSQLHelper.COL1_TASK + " = '" + todoTaskItem + "'";

        todoListSQLHelper = new TodoListSQLHelper(getContext());
        SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
        sqlDB.execSQL(deleteTodoItemSql);
        updateTodoList();
        sqlDB.close();
    }
}