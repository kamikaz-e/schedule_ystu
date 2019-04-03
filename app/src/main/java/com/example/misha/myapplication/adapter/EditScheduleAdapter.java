package com.example.misha.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.misha.myapplication.Lesson;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class EditScheduleAdapter extends RecyclerView.Adapter<EditScheduleAdapter.ViewHolder> {

    private List<Lesson> lessonList;
    private ArrayList<Subject> subjectList = new ArrayList<>();
    private ArrayList<String> audienceList = new ArrayList<>();
    private ArrayList<String> educatorList = new ArrayList<>();
    private EditScheduleCallback callback;

    public EditScheduleAdapter(EditScheduleCallback editScheduleCallback) {
        this.callback = editScheduleCallback;
    }


    public void setLessonList(List<Lesson> lessonList) {
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

    public void setSubjects(ArrayList<Subject> subjectList) {
        this.subjectList = subjectList;

    }

    public void setEducators(ArrayList<String> educatorList) {
        this.educatorList = educatorList;
    }

    public void setAudiences(ArrayList<String> audienceList) {
        this.audienceList = audienceList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener, View.OnClickListener {
        private final TextView number;
        private final TextView timeEdit;
        private final TextView subjectEdit;
        private final Spinner audienceEdit;
        private final Spinner educator;
        private final RadioGroup typeLesson;

        private ArrayAdapter subjectAdapter;

        private ArrayAdapter audienceAdapter;

        private ArrayAdapter edicatorAdapter;

        public ViewHolder(View view) {
            super(view);
            number = view.findViewById(R.id.number_monday);
            timeEdit = view.findViewById(R.id.time_monday);
            subjectEdit = view.findViewById(R.id.button_subject);
            audienceEdit = view.findViewById(R.id.spinner_audience);
            educator = view.findViewById(R.id.spinner_educator);
            typeLesson = view.findViewById(R.id.typeEdit_monday);
            /*subjectAdapter = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, subjectList);
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
            audienceAdapter = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, audienceList);
            audienceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edicatorAdapter = new ArrayAdapter<>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, educatorList);
            edicatorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           // subjectEdit.setAdapter(subjectAdapter);
            view.setOnClickListener(this);
            audienceEdit.setAdapter(audienceAdapter);
            educator.setAdapter(edicatorAdapter);
            subjectEdit.setOnClickListener(this);
            educator.setOnItemSelectedListener(this);
            audienceEdit.setOnItemSelectedListener(this);

        }

        public void onBindView(int position) {
            Lesson lesson = lessonList.get(position);
            number.setText(lesson.getId());
            timeEdit.setText(lesson.getTime());
            //typeLesson.setText(lesson.getTypeLesson());
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           /* if (parent.getId() == R.id.spinner_subject) {
                callback.onEducatorSelected(getAdapterPosition(), subjectList.get(position));
            }*/

            if (parent.getId() == R.id.spinner_audience) {
                callback.onAudienceSelected(getAdapterPosition(), audienceList.get(position));
            }

            if (parent.getId() == R.id.spinner_educator) {
                callback.onEducatorSelected(getAdapterPosition(), educatorList.get(position));
            }

        }

        /*@Override
        public void onClick(DialogInterface dialog, int position) {
            callback.onSubjectClicked(getAdapterPosition(), subjectList.get(position));
        }
*/

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onClick(View v) {
            callback.onSubjectClick(getAdapterPosition(), subjectList);
        }



        /*@Override
        public void onClick(View v) {

        }*/

     /*   @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }*/



    }
}
