package com.example.misha.myapplication.adapter.Schedule;

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

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private List<Lesson> lessonList;

    public ScheduleAdapter(List<Lesson> schedule) {
        this.lessonList = schedule;
    }

    @Override
    public ScheduleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        return lessonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView number;
        private final TextView timeEdit;
        private final TextView subjectEdit;
        private final TextView audienceEdit;
        private final TextView educatorEdit;
        private final TextView typeLessonEdit;

        public MyViewHolder(View view) {
            super(view);
            number = view.findViewById(R.id.number);
            timeEdit = view.findViewById(R.id.time);
            subjectEdit = view.findViewById(R.id.subject);
            audienceEdit = view.findViewById(R.id.audience);
            educatorEdit = view.findViewById(R.id.educator);
            typeLessonEdit = view.findViewById(R.id.typelesson);
        }

        public void onBindView(int position) {
            Lesson lesson = lessonList.get(position);
            number.setText(lesson.getTimeLesson());
            Subject subject = SubjectDao.getInstance().getItemByID(Long.parseLong(lesson.getSubject()));
            Audience audience = AudienceDao.getInstance().getItemByID(Long.parseLong(lesson.getAudience()));
            Educator educator = EducatorDao.getInstance().getItemByID(Long.parseLong(lesson.getEducator()));
            Typelesson typelesson = TypelessonDao.getInstance().getItemByID(Long.parseLong(lesson.getTypeLesson()));

            if (subject == null) {
                subjectEdit.setText("Предмет");
            } else {
                subjectEdit.setText(subject.getName());
            }
            if (audience == null) {
                audienceEdit.setText("Аудитория");
            } else {
                audienceEdit.setText(audience.getName());
            }
            if (educator == null) {
                educatorEdit.setText("Преподаватель");
            } else {
                educatorEdit.setText(educator.getName());
            }
            if (typelesson == null) {
                typeLessonEdit.setText("Тип занятия");
            } else {
                typeLessonEdit.setText(typelesson.getName());
            }
        }

    }
}