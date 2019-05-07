package com.example.misha.myapplication.module.schedule.edit.page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;
import com.example.misha.myapplication.data.database.entity.SimpleItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class FragmentListAdapter extends RecyclerView.Adapter<FragmentListAdapter.ViewHolder> {

    private List<SimpleItem> listAudience;

    private SimpleItemClickListener itemClickListener;

    public FragmentListAdapter(ArrayList<SimpleItem> audience, SimpleItemClickListener simpleItemClickListener) {
        this.listAudience = audience;
        this.itemClickListener = simpleItemClickListener;
    }

    @Override
    public FragmentListAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
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
            SimpleItem aud = listAudience.get(position);
            audience.setText(aud.getName());

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}

