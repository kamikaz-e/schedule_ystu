package com.example.misha.myapplication.adapter.editScheduleListAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;
import com.example.misha.myapplication.database.entity.Audience;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ListAudienceAdapter extends RecyclerView.Adapter<ListAudienceAdapter.ViewHolder> {

    private List<Audience> listAudience;

    private SimpleItemClickListener itemClickListener;

    public ListAudienceAdapter(ArrayList<Audience> audience, SimpleItemClickListener simpleItemClickListener) {
        this.listAudience = audience;
        this.itemClickListener = simpleItemClickListener;
    }

    @Override
    public ListAudienceAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
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
        return listAudience.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView audience;

        private ViewHolder(View view) {
            super(view);
            audience = view.findViewById(R.id.item);
            view.setOnClickListener(this);

        }

        private void onBindView(int position) {
            Audience aud = listAudience.get(position);
            audience.setText(aud.getName());

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}

