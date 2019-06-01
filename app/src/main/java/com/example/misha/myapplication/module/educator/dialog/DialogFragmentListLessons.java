package com.example.misha.myapplication.module.educator.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseAlertDialog;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.entity.LessonsEducator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.misha.myapplication.Constants.FRAGMENT_CODE;
import static com.example.misha.myapplication.module.educator.SearchEducatorFragmentView.LESSON_LIST;
import static com.example.misha.myapplication.module.educator.SearchEducatorFragmentView.NAME_EDUCATOR;

//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.

public class DialogFragmentListLessons extends BaseAlertDialog implements DialogFragmentListLessonsView {

    private DialogFragmentPresenter presenter;
    private RecyclerView rvItems;

    public static DialogFragmentListLessons newInstance(Bundle args) {
        DialogFragmentListLessons fragment = new DialogFragmentListLessons();
        fragment.setArguments(args);
        return fragment;
    }

    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ArrayList<LessonsEducator> listItems = getArguments().getParcelableArrayList(LESSON_LIST);
        String nameEducator = getArguments().getString(NAME_EDUCATOR);
        presenter = new DialogFragmentPresenter();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        @SuppressLint("InflateParams")
        View view = layoutInflater.inflate(R.layout.dialog_rv_lessons_educator, null);
        TextView title_dialog = view.findViewById(R.id.dialog_textView);
        title_dialog.setText(nameEducator);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        rvItems = view.findViewById(R.id.rv_dialog);
        loadItemsAdapter(listItems);
        Button button_cancel = view.findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(v -> dismiss());
        return builder.create();
    }


    public void loadItemsAdapter(ArrayList<LessonsEducator> lessonList) {
        DialogFragmentListLessonsAdapter dialogFragmentListLessonsAdapter = new DialogFragmentListLessonsAdapter(lessonList);
        rvItems.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvItems.setAdapter(dialogFragmentListLessonsAdapter);
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }
}
