package com.example.misha.myapplication;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.dao.TypelessonDao;
import com.example.misha.myapplication.database.entity.Lesson;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Lesson> schedule;

    public MyAdapter(List<Lesson> schedule) {
        this.schedule = schedule;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView number;
        private final TextView timeEdit;
        private final TextView subjectEdit;
        private final TextView audienceEdit;
        private final TextView educator;
        private final TextView typeLesson;

        public MyViewHolder(View view) {
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
            timeEdit.setText(lesson.getTimeLesson());
            subjectEdit.setText(SubjectDao.getInstance().getItemByID(Long.parseLong(lesson.getId())).getName());
            audienceEdit.setText(AudienceDao.getInstance().getItemByID(Long.parseLong(lesson.getId())).getName());
            educator.setText(EducatorDao.getInstance().getItemByID(Long.parseLong(lesson.getId())).getName());
            typeLesson.setText(TypelessonDao.getInstance().getItemByID(Long.parseLong(lesson.getId())).getName());
        }

    }
}