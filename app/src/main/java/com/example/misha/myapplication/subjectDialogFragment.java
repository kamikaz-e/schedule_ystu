package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.misha.myapplication.adapter.ListSubjectsAdapter;
import com.example.misha.myapplication.data.ScheduleDB;
import com.example.misha.myapplication.model.Subject;

import java.util.ArrayList;


//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.
public class subjectDialogFragment extends DialogFragment {

    private ScheduleDB ScheduleDB;

    public static final String SUBJECTS = "SUBJECTS";
    public static final String POSITION = "POSITION";

    private int clickedPosition;
    private ListSubjectsAdapter listSubjectAdapter;
    private ArrayList<Subject> listSubjects;
    private RecyclerView rvSubject;

    public static subjectDialogFragment newInstance(int position, ArrayList<Subject> subjects) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(SUBJECTS, subjects);
        args.putInt(POSITION, position);
        subjectDialogFragment fragment = new subjectDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ScheduleDB = new ScheduleDB();
        clickedPosition = getArguments().getInt(POSITION);
        listSubjects = getArguments().getParcelableArrayList(SUBJECTS);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.rv_list_subject, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        rvSubject =  view.findViewById(R.id.rv_subject_list);
        listSubjectAdapter =  new ListSubjectsAdapter(listSubjects);
        rvSubject.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        rvSubject.setAdapter(listSubjectAdapter);
        rvSubject.addOnItemTouchListener(new RecyclerListener(view.getContext(), rvSubject, new RecyclerListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
             //   Subject subj = listSubjects.get(position);
                Toast.makeText(view.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        Button button_add = view.findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });
        Button button_cancel = view.findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });
        return builder.create();
    }

}
