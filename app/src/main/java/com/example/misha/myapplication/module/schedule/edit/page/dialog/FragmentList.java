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

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.data.database.entity.SimpleItem;
import com.example.misha.myapplication.module.schedule.edit.page.FragmentListAdapter;
import com.example.misha.myapplication.module.schedule.edit.page.PageFragmentView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.
public class FragmentList extends DialogFragment {




    public static final String ITEMS = "ITEMS";
    public static final String FRAGMENT_CODE = "FRAGMENT_CODE";
    public static final String ITEMS_LIST = "ITEMS_LIST";
    public static final String POSITION = "POSITION";

    private int clickedPosition;

    private ArrayList<SimpleItem> listItems;

    public static FragmentList newInstance(Bundle args) {
        FragmentList fragment = new FragmentList();
        fragment.setArguments(args);
        return fragment;
    }


    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        clickedPosition = getArguments().getInt(POSITION);
        listItems = getArguments().getParcelableArrayList(ITEMS);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        int fragmentCode = getArguments().getInt(FRAGMENT_CODE);
        View view = layoutInflater.inflate(R.layout.dialog_rv_list, null);
        TextView title_dialog = view.findViewById(R.id.dialog_textView);
        if (fragmentCode == PageFragmentView.SUBJECT) {title_dialog.setText("Предметы");}
        if (fragmentCode == PageFragmentView.AUDIENCE) {title_dialog.setText("Аудитории");}
        if (fragmentCode == PageFragmentView.EDUCATOR) {title_dialog.setText("Преподаватели");}
        if (fragmentCode == PageFragmentView.TYPELESSON) {title_dialog.setText("Тип занятий");}
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        RecyclerView rvAudience = view.findViewById(R.id.rv_dialog);
        FragmentListAdapter fragmentListAdapter = new FragmentListAdapter(listItems, (position, view1) -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, clickedPosition);
            intent.putExtra(ITEMS_LIST, listItems.get(position));
            getParentFragment().onActivityResult(fragmentCode, Activity.RESULT_OK, intent);
            dismiss();
        });
        rvAudience.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvAudience.setAdapter(fragmentListAdapter);
        Button button_add = view.findViewById(R.id.button_add);
        button_add.setOnClickListener(v -> {

        });
        Button button_cancel = view.findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(v -> dismiss());
        return builder.create();
    }

}
