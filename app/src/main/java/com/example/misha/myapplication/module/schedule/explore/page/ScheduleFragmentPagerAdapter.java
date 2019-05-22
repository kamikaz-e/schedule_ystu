package com.example.misha.myapplication.module.schedule.explore.page;

import android.annotation.SuppressLint;
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
import com.example.misha.myapplication.entity.Audience;
import com.example.misha.myapplication.entity.Calls;
import com.example.misha.myapplication.entity.Educator;
import com.example.misha.myapplication.entity.Lesson;
import com.example.misha.myapplication.entity.Subject;
import com.example.misha.myapplication.entity.Typelesson;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ScheduleFragmentPagerAdapter extends RecyclerView.Adapter {

    private ArrayList<Lesson> lessonList = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {

        Lesson lesson = lessonList.get(position);
        Subject subject = SubjectDao.getInstance().getItemByID(Long.parseLong(lesson.getSubject()));
        Audience audience = AudienceDao.getInstance().getItemByID(Long.parseLong(lesson.getAudience()));
        Educator educator = EducatorDao.getInstance().getItemByID(Long.parseLong(lesson.getEducator()));
        Typelesson typelesson = TypelessonDao.getInstance().getItemByID(Long.parseLong(lesson.getTypeLesson()));
        if ((lessonList.get(position).getSubject().equals("0") || subject == null) ||
                (lessonList.get(position).getAudience().equals("0") || audience == null) ||
                (lessonList.get(position).getEducator().equals("0") || educator == null) ||
                (lessonList.get(position).getTypeLesson().equals("0") || typelesson == null)) {
            return 1;
        }
        return 0;
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
                return new ViewHolderLesson(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_lesson, parent, false);
                return new ViewHolderEmptyLesson(view);
        }
        return null;
    }

    public void setLessonList(ArrayList<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                Lesson lesson = lessonList.get(position);
                ArrayList<Calls> callsList = CallDao.getInstance().getAllData();
                ((ViewHolderLesson) holder).timeEditOne.setText(callsList.get(position * 2).getName());
                ((ViewHolderLesson) holder).timeEditTwo.setText(callsList.get((position * 2) + 1).getName());

                Subject subject = SubjectDao.getInstance().getItemByID(Long.parseLong(lesson.getSubject()));
                Audience audience = AudienceDao.getInstance().getItemByID(Long.parseLong(lesson.getAudience()));
                Educator educator = EducatorDao.getInstance().getItemByID(Long.parseLong(lesson.getEducator()));
                Typelesson typelesson = TypelessonDao.getInstance().getItemByID(Long.parseLong(lesson.getTypeLesson()));

                if (subject == null) {
                    break;
                } else {
                    ((ViewHolderLesson) holder).subjectHint.setHint("Предмет");
                    ((ViewHolderLesson) holder).subjectEdit.setText(subject.getName());
                }
                if (audience == null) {
                    break;
                } else {
                    ((ViewHolderLesson) holder).audienceHint.setHint("Аудитория");
                    ((ViewHolderLesson) holder).audienceEdit.setText(audience.getName());
                }
                if (educator == null) {
                    break;
                } else {
                    ((ViewHolderLesson) holder).educatorHint.setHint("Преподаватель");
                    ((ViewHolderLesson) holder).educatorEdit.setText(educator.getName());
                }
                if (typelesson == null) {
                    break;
                } else {
                    ((ViewHolderLesson) holder).typelessonEdit.setText(typelesson.getName());
                }
                break;

            case 1:
                callsList = CallDao.getInstance().getAllData();
                ((ViewHolderEmptyLesson)holder).timeEditOne.setText(callsList.get(position * 2).getName());
                ((ViewHolderEmptyLesson)holder).timeEditTwo.setText( " - " + callsList.get((position * 2) + 1).getName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lessonList == null ? 0 : lessonList.size();
    }

    public static class ViewHolderEmptyLesson extends RecyclerView.ViewHolder {

        private final TextView timeEditOne;
        private final TextView timeEditTwo;

        public ViewHolderEmptyLesson(View view) {
            super(view);
            timeEditOne = view.findViewById(R.id.timeOne);
            timeEditTwo = view.findViewById(R.id.timeTwo);
        }
    }

    public class ViewHolderLesson extends RecyclerView.ViewHolder {

        private final TextView timeEditOne;
        private final TextView timeEditTwo;
        private final TextView subjectEdit;
        private final TextView audienceEdit;
        private final TextView educatorEdit;
        private final TextView typelessonEdit;
        private final TextInputLayout subjectHint;
        private final TextInputLayout audienceHint;
        private final TextInputLayout educatorHint;


        public ViewHolderLesson(View view) {
            super(view);
            timeEditOne = view.findViewById(R.id.timeOne);
            timeEditTwo = view.findViewById(R.id.timeTwo);
            subjectEdit = view.findViewById(R.id.subject);
            audienceEdit = view.findViewById(R.id.audience);
            educatorEdit = view.findViewById(R.id.educator);
            typelessonEdit = view.findViewById(R.id.typelesson);
            subjectHint = view.findViewById(R.id.subject_hint);
            audienceHint = view.findViewById(R.id.audience_hint);
            educatorHint = view.findViewById(R.id.educator_hint);

        }
    }
}
