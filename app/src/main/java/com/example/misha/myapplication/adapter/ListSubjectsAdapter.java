package com.example.misha.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.myapplication.Lesson;
import com.example.misha.myapplication.R;

import java.util.List;

public class ListSubjectsAdapter extends RecyclerView.Adapter<ListSubjectsAdapter.ViewHolder> {

    private List<Lesson> listSubjects;

    public ListSubjectsAdapter(List<Lesson> schedule) {
        this.listSubjects = schedule;
    }

    @Override
    public ListSubjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View view) {
            super(view);

        }

        public void onBindView(int position) {
            Lesson lesson = listSubjects.get(position);

        }

    }
}

