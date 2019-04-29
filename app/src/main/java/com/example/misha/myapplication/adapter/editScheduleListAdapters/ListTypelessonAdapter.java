package com.example.misha.myapplication.adapter.editScheduleListAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;
import com.example.misha.myapplication.database.entity.Typelesson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListTypelessonAdapter extends RecyclerView.Adapter<ListTypelessonAdapter.ViewHolder> {

    private List<Typelesson> listTypelesson;
    private SimpleItemClickListener itemClickListener;

    
    public ListTypelessonAdapter(ArrayList<Typelesson> typelesson, SimpleItemClickListener simpleItemClickListener) {
        this.listTypelesson = typelesson;
        this.itemClickListener = simpleItemClickListener;
    }

    @Override
    public ListTypelessonAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
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
        return listTypelesson.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView typelesson;

        ViewHolder(View view) {
            super(view);
            typelesson = view.findViewById(R.id.item);
            view.setOnClickListener(this);

        }

        void onBindView(int position) {
            Typelesson typ = listTypelesson.get(position);
            typelesson.setText(typ.getName());

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}

