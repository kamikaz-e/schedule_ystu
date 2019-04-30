package com.example.misha.myapplication.module.schedule.edit.page.editScheduleListAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;
import com.example.misha.myapplication.data.database.entity.Subject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListSubjectsAdapter extends RecyclerView.Adapter<ListSubjectsAdapter.ViewHolder> {

    private List<Subject> listSubjects;
    private SimpleItemClickListener itemClickListener;


    public ListSubjectsAdapter(ArrayList<Subject> subject, SimpleItemClickListener simpleItemClickListener) {
        this.listSubjects = subject;
        this.itemClickListener = simpleItemClickListener;
    }

    @Override
    public ListSubjectsAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, final int position) {
        holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return listSubjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView subject;

        private ViewHolder(View view) {
            super(view);
            subject = view.findViewById(R.id.item);
            view.setOnClickListener(this);

        }

        private void onBindView(int position) {
            Subject subj = listSubjects.get(position);
            subject.setText(subj.getName());

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}

