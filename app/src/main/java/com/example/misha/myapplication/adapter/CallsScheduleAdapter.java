package com.example.misha.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.R;

import androidx.recyclerview.widget.RecyclerView;


public class CallsScheduleAdapter extends RecyclerView.Adapter<CallsScheduleAdapter.RecyclerVH> {

    Context c;


    public CallsScheduleAdapter(Context c) {
        this.c = c;
    }

    @Override
    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_row_call, parent, false);
        return new RecyclerVH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerVH holder, int position) {
        holder.nameTxt.setText("asdasdas");
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class RecyclerVH extends RecyclerView.ViewHolder {
        TextView nameTxt;

        public RecyclerVH(View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.number);
        }
    }
}