package com.example.misha.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.model.Educator;

import java.util.ArrayList;
import java.util.List;

public class ListEducatorAdapter extends RecyclerView.Adapter<ListEducatorAdapter.ViewHolder> {

    private List<Educator> listEducators;

    private SimpleItemClickListener itemClickListener;

    public ListEducatorAdapter(ArrayList<Educator> schedule, SimpleItemClickListener simpleItemClickListener) {
        this.listEducators = schedule;
        this.itemClickListener = simpleItemClickListener;
    }

    @Override
    public ListEducatorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        return listEducators.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView educator;

        public ViewHolder(View view) {
            super(view);
            educator = view.findViewById(R.id.item);
            view.setOnClickListener(this);

        }

        public void onBindView(int position) {
            Educator educ = listEducators.get(position);
            educator.setText(educ.getName());

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}

