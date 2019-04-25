package com.example.misha.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.editSchedule.CallScheduleCallback;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.entity.Calls;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class CallsScheduleAdapter extends RecyclerView.Adapter<CallsScheduleAdapter.ViewHolder> {


    private ArrayList<Calls> callsList = new ArrayList<>();

    private CallScheduleCallback itemClickListener;


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

    public void setCallsList(ArrayList<Calls> callsList) {
        this.callsList = callsList;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CardView cardViewCall;
        private final TextView numberCall;
        private final TextView timeCall;

        public ViewHolder(View view) {
            super(view);
            cardViewCall = view.findViewById(R.id.call_cardView);
            numberCall = view.findViewById(R.id.call_number);
            timeCall = view.findViewById(R.id.call_time);
            cardViewCall.setOnClickListener(this);
            timeCall.setOnClickListener(this);
            view.setOnClickListener(this);

        }

        public void onBindView(int position) {
            callsList = CallDao.getInstance().getAllData();
            Calls calls = callsList.get(position);
            numberCall.setText(calls.getId());
            timeCall.setText(calls.getName());


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.call_time || v.getId() == R.id.call_cardView) {
                itemClickListener.onCallClick(getAdapterPosition());
            }
        }
    }
}