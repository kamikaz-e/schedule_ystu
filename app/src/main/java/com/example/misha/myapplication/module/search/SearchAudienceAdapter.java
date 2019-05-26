package com.example.misha.myapplication.module.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Groups;

import java.util.ArrayList;


public class SearchAudienceAdapter extends RecyclerView.Adapter<SearchAudienceAdapter.MyViewHolder>
        implements Filterable {

    private ArrayList<Audience> contactList;
    private ArrayList<Audience> contactListFiltered;
    private GroupsAdapterListener listener;


    public SearchAudienceAdapter(ArrayList<Audience> contactList, GroupsAdapterListener listener) {
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Audience audience = contactListFiltered.get(position);
        holder.name.setText(audience.getName());
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    ArrayList<Audience> filteredList = new ArrayList<>();
                    for (Audience row : contactList) {
                        if (row.getName().contains(charString.toLowerCase()) || row.getName().contains(charSequence) || row.getName().contains(charString.toUpperCase())) {
                            filteredList.add(row);
                        }
                    }
                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Audience>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(contactListFiltered.get(getAdapterPosition()), v);
        }
    }

    public interface GroupsAdapterListener {
        void onItemClick(Audience contact, View v);
    }
}
