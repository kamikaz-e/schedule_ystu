package com.example.misha.myapplication.module.schedule.edit.page.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.module.schedule.edit.page.editScheduleListAdapters.ListSubjectsAdapter;
import com.example.misha.myapplication.data.database.entity.Subject;
import com.example.misha.myapplication.module.editData.EditData;

import java.util.ArrayList;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.
public class SubjectList extends DialogFragment {

    public static final int SUBJECT_CODE = 3432;

    public static final String SUBJECTS = "SUBJECTS";
    public static final String SUBJECT_LIST = "SUBJECT_LIST";
    public static final String POSITION = "POSITION";

    private int clickedPosition;
    private ListSubjectsAdapter listSubjectAdapter;
    private ArrayList<Subject> listSubjects;
    private RecyclerView rvSubject;

    public static SubjectList newInstance(int position, ArrayList<Subject> subjects) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(SUBJECTS, subjects);
        args.putInt(POSITION, position);
        SubjectList fragment = new SubjectList();
        fragment.setArguments(args);
        return fragment;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        clickedPosition = getArguments().getInt(POSITION);
        listSubjects = getArguments().getParcelableArrayList(SUBJECTS);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.dialog_rv_list, null);
        TextView title_dialog = view.findViewById(R.id.dialog_textView);
        title_dialog.setText("Предмет");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        rvSubject = view.findViewById(R.id.rv_dialog);
        listSubjectAdapter = new ListSubjectsAdapter(listSubjects, (position, view1) -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, clickedPosition);
            intent.putExtra(SUBJECT_LIST, listSubjects.get(position));
            getParentFragment().onActivityResult(SUBJECT_CODE, Activity.RESULT_OK, intent);
            dismiss();
        });
        rvSubject.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        rvSubject.setAdapter(listSubjectAdapter);

        Button button_add = view.findViewById(R.id.button_add);
        button_add.setOnClickListener(v -> {
            EditData fragment = new EditData();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        });
        Button button_cancel = view.findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(v -> dismiss());
        return builder.create();
    }

}