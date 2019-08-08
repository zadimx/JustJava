package com.example.android.justjava.log;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.justjava.R;
import com.example.android.justjava.ui.NotesAdapter;

public class TableLogActivity extends AppCompatActivity {

    private static LogAdapter logAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_log);
        RecyclerView recyclerView = findViewById(R.id.log_rv);

        logAdapter = new LogAdapter(null, onLogClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(logAdapter);
        logAdapter.notifyDataSetChanged();
    }


    private final LogAdapter.OnLogClickListener onLogClickListener = new LogAdapter.OnLogClickListener() {
        @Override
        public void onLogClick(long noteId) {

        }
    };
}
