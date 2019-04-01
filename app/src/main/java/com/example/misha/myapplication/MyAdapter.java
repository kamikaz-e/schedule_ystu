package com.example.misha.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    List<data_schedule> schedule;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView Number;
        public TextView TimeEdit;
        public TextView SubjectEdit;
        public TextView AudienceEdit;
        public TextView Educator;
        public TextView TypeLesson;

        public MyViewHolder(View v) {
            super(v);

            mCardView =  v.findViewById(R.id.card_view);
            Number =  v.findViewById(R.id.number);
            TimeEdit =  v.findViewById(R.id.time);
            SubjectEdit=v.findViewById(R.id.subject);
            AudienceEdit=v.findViewById(R.id.Audience);
            Educator=v.findViewById(R.id.educator);
            TypeLesson=v.findViewById(R.id.type);
        }
    }

    MyAdapter(List<data_schedule> schedule) {
        this.schedule = schedule;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.card_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh; }

    @Override public void onBindViewHolder(MyViewHolder holder, final int position)
    {

        holder.Number.setText(schedule.get(position).id);
        holder.TimeEdit.setText(schedule.get(position).timelesson);
        holder.SubjectEdit.setText(schedule.get(position).subjectEdit);
        holder.AudienceEdit.setText(schedule.get(position).audienceEdit);
        holder.Educator.setText(schedule.get(position).educator);
        holder.TypeLesson.setText(schedule.get(position).typelesson);
    }


    @Override public int getItemCount() {
        return schedule.size(); }
}