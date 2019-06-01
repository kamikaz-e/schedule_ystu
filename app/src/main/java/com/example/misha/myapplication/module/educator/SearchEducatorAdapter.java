package com.example.misha.myapplication.module.educator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Educator;
import com.example.misha.myapplication.module.groups.GroupsAdapter;

import java.util.ArrayList;


public class SearchEducatorAdapter extends RecyclerView.Adapter<SearchEducatorAdapter.MyViewHolder>
        implements Filterable {

    private ArrayList<Educator> educatorList;
    private ArrayList<Educator> educatorListFiltered;
    private SearchEducatorsAdapterListener listener;


    public SearchEducatorAdapter(ArrayList<Educator> educatorList, SearchEducatorsAdapterListener listener) {
        this.listener = listener;
        this.educatorList = educatorList;
        this.educatorListFiltered = educatorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Educator educator = educatorListFiltered.get(position);
        holder.name.setText(educator.getName());
    }

    @Override
    public int getItemCount() {
        return educatorListFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    educatorListFiltered = educatorList;
                } else {
                    ArrayList<Educator> filteredList = new ArrayList<>();
                    for (Educator row : educatorList) {
                        if (row.getName().contains(charString.toLowerCase()) || row.getName().contains(charSequence) || row.getName().contains(charString.toUpperCase())) {
                            filteredList.add(row);
                        }
                    }
                    educatorListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = educatorListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                educatorListFiltered = (ArrayList<Educator>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(educatorListFiltered.get(getAdapterPosition()), v);
        }
    }
    public interface SearchEducatorsAdapterListener {
        void onItemClick(Educator educator, View v);
    }

}
