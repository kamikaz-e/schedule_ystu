package com.example.misha.myapplication.adapter.TabDays;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.dao.TypelessonDao;
import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Educator;
import com.example.misha.myapplication.database.entity.Lesson;
import com.example.misha.myapplication.database.entity.Subject;
import com.example.misha.myapplication.database.entity.Typelesson;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TabDaysAdapter extends RecyclerView.Adapter<TabDaysAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tab_day, parent, false);
        return new ViewHolder(view);
    }

    public void setLessonList(List<Lesson> lessonList) {

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final TextView dateWeek;


        public ViewHolder(View view) {
            super(view);;
            date = view.findViewById(R.id.time);
            dateWeek = view.findViewById(R.id.subject);
        }

        public void onBindView(int position) {

            date.setText();
            dateWeek.setText();


        }

    }
}