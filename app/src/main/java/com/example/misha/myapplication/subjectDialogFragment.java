package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.misha.myapplication.adapter.ListSubjectsAdapter;
import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;
import com.example.misha.myapplication.model.Subject;

import java.util.ArrayList;


//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.
public class subjectDialogFragment extends DialogFragment {

    private ScheduleDB ScheduleDB;

    public static final String SUBJECTS = "SUBJECTS";
    public static final String POSITION = "POSITION";

    private int clickedPosition;
    private ListSubjectsAdapter lsa;
    private ArrayList<Subject> subjects;
    private RecyclerView rv;

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
        subjects = getArguments().getParcelableArrayList(SUBJECTS);
        LayoutInflater li = LayoutInflater.from(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AppCompatAlertDialogStyle);
        View view = li.inflate(R.layout.recycler_view_subject_list, null);
        builder.setView(view);
        rv =  view.findViewById(R.id.rv_subject_list);
        lsa =  new ListSubjectsAdapter(subjects);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(lsa);


       /* Dialog dialog = new Dialog(getBaseContext());
        dialog.setContentView();*/


        builder.setCancelable(false).setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dismiss();
            }
        });
        return builder.create();
    }

}
