package com.example.misha.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.SimpleItemClickListener;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.entity.Calls;
import com.example.misha.myapplication.database.entity.Lesson;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CallsScheduleAdapter extends RecyclerView.Adapter<CallsScheduleAdapter.ViewHolder> {

    private List<Lesson> lessonList;
    private ArrayList<Calls> callsList = new ArrayList<>();
    private SimpleItemClickListener callback;

    public CallsScheduleAdapter(SimpleItemClickListener editScheduleCallback) {
        this.callback = editScheduleCallback;
    }


    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public void setCalls(ArrayList<Calls> callsList) {
        this.callsList = callsList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_call, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.onBindView(position);
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView number;
        private final TextView subjectEdit;


        public ViewHolder(View view) {
            super(view);
            number = view.findViewById(R.id.number);
            subjectEdit = view.findViewById(R.id.time);

            subjectEdit.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        public void onBindView(int position) {
            Calls calls = callsList.get(position);
           number.setText("a");
           subjectEdit.setText("b");

        }

        @Override
        public void onClick(View v) {

            callback.onItemClick(getAdapterPosition(), v);
        }
    }
}
