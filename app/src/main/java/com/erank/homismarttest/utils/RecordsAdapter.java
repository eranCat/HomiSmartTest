package com.erank.homismarttest.utils;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erank.homismarttest.R;
import com.erank.homismarttest.models.Action;
import com.erank.homismarttest.models.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordVH> {

    private List<User.Record> records;

    public RecordsAdapter() {
        records = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecordVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.record_cell, parent, false);
        return new RecordVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordVH holder, int i) {
        holder.fill(records.get(i));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void setRecords(List<User.Record> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    static class RecordVH extends RecyclerView.ViewHolder {

        TextView dateTv, actionTv;

        RecordVH(@NonNull View itemView) {
            super(itemView);

            dateTv = itemView.findViewById(R.id.date_tv);
            actionTv = itemView.findViewById(R.id.action_tv);
        }

        public void fill(User.Record record) {
            Action action = record.action;
            actionTv.setText(action.toString().toLowerCase());

            @ColorRes int colorRes = action == Action.OUT ?
                    R.color.colorOutAction
                    : R.color.colorInAction;

            int color = itemView.getContext().getResources().getColor(colorRes);

            actionTv.setTextColor(color);
            DateFormat format = SimpleDateFormat.getDateTimeInstance();
            String formattedDate = format.format(record.date);
            dateTv.setText(formattedDate);
        }
    }
}
