package com.example.misha.myapplication.module.groups;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;
import com.example.misha.myapplication.entity.Groups;
import com.example.misha.myapplication.entity.Request;

import java.util.ArrayList;


public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder>
        implements Filterable {

    private ArrayList<Groups> contactList;
    private ArrayList<Groups> contactListFiltered;
    private SimpleItemClickListener listener;


    public GroupsAdapter(ArrayList<Groups> contactList, SimpleItemClickListener listener) {
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
        final Groups groups = contactListFiltered.get(position);
        holder.name.setText(groups.getName());
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
                    ArrayList<Groups> filteredList = new ArrayList<>();
                    for (Groups row : contactList) {
                        if (row.getName().contains(charString.toLowerCase()) || row.getName().contains(charSequence)|| row.getName().contains(charString.toUpperCase())) {
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
                contactListFiltered = (ArrayList<Groups>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item);

            view.setOnClickListener(view1 -> view.setOnClickListener(this));
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition(), v);
        }
    }


}
