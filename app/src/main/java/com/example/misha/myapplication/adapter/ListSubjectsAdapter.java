package com.example.misha.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class ListSubjectsAdapter extends RecyclerView.Adapter<ListSubjectsAdapter.ViewHolder> {

    private List<Subject> listSubjects;

    private SimpleItemClickListener itemClickListener;

    public ListSubjectsAdapter(ArrayList<Subject> schedule, SimpleItemClickListener simpleItemClickListener) {
        this.listSubjects = schedule;
        this.itemClickListener = simpleItemClickListener;
    }

    @Override
    public ListSubjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return listSubjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView subject;

        public ViewHolder(View view) {
            super(view);
            subject = view.findViewById(R.id.item);
            view.setOnClickListener(this);

        }

        public void onBindView(int position) {
            Subject subj = listSubjects.get(position);
            subject.setText(subj.getName());

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}

