package com.example.misha.myapplication.module.educator.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.data.database.dao.CallDao;
import com.example.misha.myapplication.entity.Calls;
import com.example.misha.myapplication.entity.LessonsEducator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class DialogFragmentListLessonsAdapter extends RecyclerView.Adapter<DialogFragmentListLessonsAdapter.ViewHolder> {

    private ArrayList<Calls> callsList = new ArrayList<>();
    private List<LessonsEducator> lessonList;


    public DialogFragmentListLessonsAdapter(ArrayList<LessonsEducator> items) {
        this.lessonList = items;
    }

    @Override
    public DialogFragmentListLessonsAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, final int position) {
        holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView subject;
        private final TextView audience;
        private final TextView typelesson;
        private final TextView timeOne;
        private final TextView timeTwo;

        private ViewHolder(View view) {
            super(view);
            subject = view.findViewById(R.id.subject);
            audience = view.findViewById(R.id.audience);
            typelesson = view.findViewById(R.id.typelesson);
            timeOne = view.findViewById(R.id.timeOne);
            timeTwo = view.findViewById(R.id.timeTwo);

        }

        private void onBindView(int position) {
            LessonsEducator item = lessonList.get(position);
            subject.setText(item.getName_subject());
            audience.setText(item.getName_audience());
            typelesson.setText(item.getName_typelesson());
            timeOne.setText(item.getNumber_lesson());
            timeTwo.setText(item.getName_subject());
            callsList = CallDao.getInstance().getAllData();
            timeOne.setText(callsList.get(item.getNumber_lesson() * 2).getName());
            timeTwo.setText(callsList.get((position * 2) + 1).getName());
        }

    }
}

