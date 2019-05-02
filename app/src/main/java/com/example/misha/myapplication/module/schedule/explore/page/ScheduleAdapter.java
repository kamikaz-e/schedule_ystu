package com.example.misha.myapplication.module.schedule.explore.page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.dao.CallDao;
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.dao.SubjectDao;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.data.database.entity.Calls;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.data.database.entity.Lesson;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.data.database.entity.Typelesson;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<Lesson> lessonList;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson, parent, false);
        return new ViewHolder(view);
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
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
        private final TextView subjectEdit;
        private final TextView audienceEdit;
        private final TextView educatorEdit;
        private final TextView typelessonEdit;
        private final TextInputLayout subjectHint;
        private final TextInputLayout audienceHint;
        private final TextInputLayout educatorHint;
        private final TextInputLayout typelessonHint;

        public ViewHolder(View view) {
            super(view);
            number = view.findViewById(R.id.number);
            timeEdit = view.findViewById(R.id.time);
            subjectEdit = view.findViewById(R.id.subject);
            audienceEdit = view.findViewById(R.id.audience);
            educatorEdit = view.findViewById(R.id.educator);
            typelessonEdit = view.findViewById(R.id.typelesson);
            subjectHint = view.findViewById(R.id.subject_hint);
            audienceHint = view.findViewById(R.id.audience_hint);
            educatorHint = view.findViewById(R.id.educator_hint);
            typelessonHint = view.findViewById(R.id.typelesson_hint);
        }

        public void onBindView(int position) {
            Lesson lesson = lessonList.get(position);
            number.setText(lesson.getTimeLesson());
            timeEdit.setText((lesson.getTimeLesson()));
            Subject subject = SubjectDao.getInstance().getItemByID(Long.parseLong(lesson.getSubject()));
            Audience audience = AudienceDao.getInstance().getItemByID(Long.parseLong(lesson.getAudience()));
            Educator educator = EducatorDao.getInstance().getItemByID(Long.parseLong(lesson.getEducator()));
            Typelesson typelesson = TypelessonDao.getInstance().getItemByID(Long.parseLong(lesson.getTypeLesson()));

            Calls calls = CallDao.getInstance().getItemByID(Long.parseLong(lesson.getTimeLesson()));
            timeEdit.setText(calls.getName());
            if (subject == null) {
                subjectHint.setHint("");
                subjectEdit.setText("Предмет");
            } else {
                subjectHint.setHint("Предмет");
                subjectEdit.setText(subject.getName());
            }
            if (audience == null) {
                audienceHint.setHint("");
                audienceEdit.setText("Аудитория");
            } else {
                audienceHint.setHint("Аудитория");
                audienceEdit.setText(audience.getName());
            }
            if (educator == null) {
                educatorHint.setHint("");
                educatorEdit.setText("Преподаватель");
            } else {
                educatorHint.setHint("Преподаватель");
                educatorEdit.setText(educator.getName());
            }
            if (typelesson == null) {
                typelessonHint.setHint("");
                typelessonEdit.setText("Тип занятия");
            } else {
                typelessonHint.setHint("Тип занятия");
                typelessonEdit.setText(typelesson.getName());
            }
        }

    }
}