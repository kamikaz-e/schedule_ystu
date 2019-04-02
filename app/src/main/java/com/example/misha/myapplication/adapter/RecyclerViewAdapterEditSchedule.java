package com.example.misha.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.Lesson;
import com.example.misha.myapplication.R;

import java.util.List;

public class RecyclerViewAdapterEditSchedule extends RecyclerView.Adapter<RecyclerViewAdapterEditSchedule.ViewHolder> {

    private List<Lesson> schedule;

    public RecyclerViewAdapterEditSchedule(List<Lesson> schedule) {
        this.schedule = schedule;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_lesson, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView number;
        private final TextView timeEdit;
        private final TextView subjectEdit;
        private final TextView audienceEdit;
        private final TextView educator;
        private final TextView typeLesson;

        public ViewHolder(View view) {
            super(view);
            number = view.findViewById(R.id.number);
            timeEdit = view.findViewById(R.id.time);
            subjectEdit = view.findViewById(R.id.subject);
            audienceEdit = view.findViewById(R.id.Audience);
            educator = view.findViewById(R.id.educator);
            typeLesson = view.findViewById(R.id.type);
        }

        public void onBindView(int position) {
            Lesson lesson = schedule.get(position);
            number.setText(lesson.getId());
            timeEdit.setText(lesson.getTime());
            subjectEdit.setText(lesson.getSubjectEdit());
            audienceEdit.setText(lesson.getAudienceEdit());
            educator.setText(lesson.getEducator());
            typeLesson.setText(lesson.getTypeLesson());
        }

    }
}
