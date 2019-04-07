package com.example.misha.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Typelesson;

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
    public ListTypelessonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        return listTypelesson.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView typelesson;

        public ViewHolder(View view) {
            super(view);
            typelesson = view.findViewById(R.id.item);
            view.setOnClickListener(this);

        }

        public void onBindView(int position) {
            Typelesson typ = listTypelesson.get(position);
            typelesson.setText(typ.getName());

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}

