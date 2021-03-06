package com.example.misha.myapplication.module.groups;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.entity.Groups;

import java.util.ArrayList;


public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder>
        implements Filterable {

    private ArrayList<Groups> groupsList;
    private ArrayList<Groups> groupsListFiltered;
    private GroupsAdapterListener listener;


    public GroupsAdapter(ArrayList<Groups> groupsList, GroupsAdapterListener listener) {
        this.listener = listener;
        this.groupsList = groupsList;
        this.groupsListFiltered = groupsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Groups groups = groupsListFiltered.get(position);
        holder.name.setText(groups.getName());
    }

    @Override
    public int getItemCount() {
        return groupsListFiltered.size();
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    groupsListFiltered = groupsList;
                } else {
                    ArrayList<Groups> filteredList = new ArrayList<>();
                    for (Groups row : groupsList) {
                        if (row.getName().contains(charString.toLowerCase()) || row.getName().contains(charSequence)|| row.getName().contains(charString.toUpperCase())) {
                            filteredList.add(row);
                        }
                    }
                    groupsListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = groupsListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                groupsListFiltered = (ArrayList<Groups>) filterResults.values;
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
            listener.onItemClick(groupsListFiltered.get(getAdapterPosition()), v);
        }
    }

    public interface GroupsAdapterListener {
        void onItemClick(Groups group, View v);
    }
}
