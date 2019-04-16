package com.example.misha.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;
import com.example.misha.myapplication.activity.MainActivity;
import com.example.misha.myapplication.adapter.editSchedule.CallScheduleCallback;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Calls;
import com.example.misha.myapplication.database.entity.Lesson;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class CallsScheduleAdapter extends RecyclerView.Adapter<CallsScheduleAdapter.ViewHolder> {

    private List<Calls> listAudience;
    private ArrayList<Audience> audienceList = new ArrayList<>();

    private CallScheduleCallback itemClickListener;
    Context context;
    public CallsScheduleAdapter(CallScheduleCallback callsScheduleCallback) {
        this.itemClickListener = callsScheduleCallback;
    }

    @Override
    public CallsScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_call, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.onBindView(position);
    }

    public void setAudiences(ArrayList<Audience> audienceList) {
        this.audienceList = audienceList;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView audience;

        public ViewHolder(View view) {
            super(view);
            audience = view.findViewById(R.id.time_row);
            audience.setOnClickListener(this);
            view.setOnClickListener(this);

        }

        public void onBindView(int position) {

            audience.setText("asdasd");

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.time_row) {
                itemClickListener.onCallClick(getAdapterPosition());
            }
        }
    }
}