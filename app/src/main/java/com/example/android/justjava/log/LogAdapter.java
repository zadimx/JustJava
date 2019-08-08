package com.example.android.justjava.log;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.justjava.DbActivity;
import com.example.android.justjava.R;
import com.example.android.justjava.ui.CursorRecyclerAdapter;
import com.example.android.justjava.ui.NotesAdapter;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {

    private final OnLogClickListener onLogClickListener;


    public LogAdapter(Cursor cursor, OnLogClickListener onLogClickListener) {
//        super(cursor);

        this.onLogClickListener = onLogClickListener;
    }
    @Override
    public LogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.view_item_log, parent, false);
        return new LogAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.axisLog1.setText(DbActivity.getArrayAxisTemp().get("axis1")[position]);
        holder.axisLog2.setText(DbActivity.getArrayAxisTemp().get("axis2")[position]);
        holder.axisLog3.setText(DbActivity.getArrayAxisTemp().get("axis3")[position]);
        holder.axisLog4.setText(DbActivity.getArrayAxisTemp().get("axis4")[position]);
    }


    @Override
    public int getItemCount() {
//        return DbActivity.getArrayAxisTemp().get("time").length;
        return 0;
    }

    /**
     * View holder
     * Хранит информацию о ячейке
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());

        private final TextView axisLog1;
        private final TextView axisLogMidNext;
        private final TextView axisLog2;
        private final TextView axisLog3;
        private final TextView axisLogMidBack;
        private final TextView axisLog4;
        private final TextView dataTime;


        public ViewHolder(View itemView) {
            super(itemView);

            this.axisLog1 = itemView.findViewById(R.id.axisLog1);
            this.axisLog2 = itemView.findViewById(R.id.axisLog2);
            this.axisLog3 = itemView.findViewById(R.id.axisLog3);
            this.axisLog4 = itemView.findViewById(R.id.axisLog4);
            this.axisLogMidNext = itemView.findViewById(R.id.axisLog_mid_next);
            this.axisLogMidBack = itemView.findViewById(R.id.axisLog_mid_back);
            this.dataTime = itemView.findViewById(R.id.data_time);
//            this.dateTv = itemView.findViewById(R.id.date_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long noteId = (Long) v.getTag();
                    onLogClickListener.onLogClick(noteId);
                }
            });

        }


    }

    public interface OnLogClickListener {
        void onLogClick(long noteId);
    }

}
