package com.example.misha.myapplication.adapter.EditSchedule;

import androidx.recyclerview.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

public class EditScheduleAdapter extends RecyclerView.Adapter<EditScheduleAdapter.ViewHolder> {

    private List<Lesson> lessonList;
    private ArrayList<Subject> subjectList = new ArrayList<>();
    private ArrayList<Audience> audienceList = new ArrayList<>();
    private ArrayList<Educator> educatorList = new ArrayList<>();
    private ArrayList<Typelesson> typelessonList = new ArrayList<>();
    private EditScheduleCallback callback;

    public EditScheduleAdapter(EditScheduleCallback editScheduleCallback) {
        this.callback = editScheduleCallback;
    }


    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public void setEducators(ArrayList<Educator> educatorList) {
        this.educatorList = educatorList;
    }

    public void setAudiences(ArrayList<Audience> audienceList) {
        this.audienceList = audienceList;
    }

    public void setSubjects(ArrayList<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public void setTypelesson(ArrayList<Typelesson> typelessonList) {
        this.typelessonList = typelessonList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson, parent, false);
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


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView number;
        private final TextView timeEdit;
        private final TextView subjectEdit;
        private final TextView audienceEdit;
        private final TextView educatorEdit;
        private final TextView typeLessonEdit;


        public ViewHolder(View view) {
            super(view);
            number = view.findViewById(R.id.number);
            timeEdit = view.findViewById(R.id.time);
            subjectEdit = view.findViewById(R.id.button_subject);
            audienceEdit = view.findViewById(R.id.button_audience);
            educatorEdit = view.findViewById(R.id.button_educator);
            typeLessonEdit = view.findViewById(R.id.button_typelesson);
            subjectEdit.setOnClickListener(this);
            audienceEdit.setOnClickListener(this);
            educatorEdit.setOnClickListener(this);
            typeLessonEdit.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        public void onBindView(int position) {
            //timeEdit.setText(lesson.getTimeLesson());
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

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button_audience) {
                callback.onAudienceClick(getAdapterPosition(), audienceList);
            }
            if (v.getId() == R.id.button_educator) {
                callback.onEducatorClick(getAdapterPosition(), educatorList);
            }
            if (v.getId() == R.id.button_subject) {
                callback.onSubjectClick(getAdapterPosition(), subjectList);
            }
            if (v.getId() == R.id.button_typelesson) {
                callback.onTypelessonClick(getAdapterPosition(), typelessonList);
            }
        }


    }
}
