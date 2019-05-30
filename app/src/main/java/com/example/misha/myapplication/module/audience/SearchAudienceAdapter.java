package com.example.misha.myapplication.module.audience;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.entity.Audience;

import java.util.ArrayList;


public class SearchAudienceAdapter extends RecyclerView.Adapter<SearchAudienceAdapter.MyViewHolder>
        implements Filterable {

    private ArrayList<Audience> audienceList;
    private ArrayList<Audience> audienceListFiltered;


    public SearchAudienceAdapter(ArrayList<Audience> audienceList) {
        this.audienceList = audienceList;
        this.audienceListFiltered = audienceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Audience audience = audienceListFiltered.get(position);
        holder.name.setText(audience.getName());
    }

    @Override
    public int getItemCount() {
        return audienceListFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    audienceListFiltered = audienceList;
                } else {
                    ArrayList<Audience> filteredList = new ArrayList<>();
                    for (Audience row : audienceList) {
                        if (row.getName().contains(charString.toLowerCase()) || row.getName().contains(charSequence) || row.getName().contains(charString.toUpperCase())) {
                            filteredList.add(row);
                        }
                    }
                    audienceListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = audienceListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                audienceListFiltered = (ArrayList<Audience>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item);
        }
    }

}
