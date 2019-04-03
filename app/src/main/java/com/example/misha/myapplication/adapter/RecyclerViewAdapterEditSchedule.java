package com.example.misha.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.misha.myapplication.Lesson;
import com.example.misha.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterEditSchedule extends RecyclerView.Adapter<RecyclerViewAdapterEditSchedule.ViewHolder> {

    private List<Lesson> lessonList;
    final ArrayList<String> subject_list = new ArrayList<>();
    final ArrayList<String> audience_list = new ArrayList<>();
    final ArrayList<String> educator_list = new ArrayList<>();


    public RecyclerViewAdapterEditSchedule(List<Lesson> lessonList) {
        this.lessonList = lessonList;
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
        return lessonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView number;
        private final TextView timeEdit;
        private final Spinner subjectEdit;
        private final Spinner audienceEdit;
        private final Spinner educator;
        private final RadioGroup typeLesson;

        public ViewHolder(View view) {
            super(view);
            number = view.findViewById(R.id.number_monday);
            timeEdit = view.findViewById(R.id.time_monday);
            subjectEdit = view.findViewById(R.id.subject_edit_monday);
            audienceEdit = view.findViewById(R.id.audience_edit_monday);
            educator = view.findViewById(R.id.educator_edit_monday);
            typeLesson = view.findViewById(R.id.typeEdit_monday);

        }

        public void onBindView(int position) {
            Lesson lesson = lessonList.get(position);
            number.setText(lesson.getId());
            timeEdit.setText(lesson.getTime());
            subjectEdit.setSelection(0);
            audienceEdit.setSelection(0);
            educator.setSelection(0);
            //typeLesson.setText(lesson.getTypeLesson());
        }

    }
}
