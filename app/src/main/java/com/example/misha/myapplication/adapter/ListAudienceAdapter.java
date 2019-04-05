package com.example.misha.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.entity.Audience;

import java.util.ArrayList;
import java.util.List;

public class ListAudienceAdapter extends RecyclerView.Adapter<ListAudienceAdapter.ViewHolder> {

    private List<Audience> listAudience;

    private SimpleItemClickListener itemClickListener;

    public ListAudienceAdapter(ArrayList<Audience> schedule, SimpleItemClickListener simpleItemClickListener) {
        this.listAudience = schedule;
        this.itemClickListener = simpleItemClickListener;
    }

    @Override
    public ListAudienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        return listAudience.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView audience;

        public ViewHolder(View view) {
            super(view);
            audience = view.findViewById(R.id.item);
            view.setOnClickListener(this);

        }

        public void onBindView(int position) {
            Audience aud = listAudience.get(position);
            audience.setText(aud.getName());

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}

