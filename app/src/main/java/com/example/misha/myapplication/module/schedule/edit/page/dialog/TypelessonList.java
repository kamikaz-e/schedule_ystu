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
import com.example.misha.myapplication.module.schedule.edit.page.editScheduleListAdapters.ListTypelessonAdapter;
import com.example.misha.myapplication.data.database.entity.Typelesson;
import com.example.misha.myapplication.fragments.EditData;

import java.util.ArrayList;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.
public class TypelessonList extends DialogFragment {

    public static final int TYPELESSON_CODE = 3433;

    public static final String TYPELESSONS = "TYPELESSONS";
    public static final String TYPELESSON_LIST = "TYPELESSON_LIST";
    public static final String POSITION = "POSITION";

    private int clickedPosition;
    private ListTypelessonAdapter listTypelessonAdapter;
    private ArrayList<Typelesson> listTypelesson;
    private RecyclerView rvTypelesson;

    public static TypelessonList newInstance(int position, ArrayList<Typelesson> typelessons) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(TYPELESSONS, typelessons);
        args.putInt(POSITION, position);
        TypelessonList fragment = new TypelessonList();
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        clickedPosition = getArguments().getInt(POSITION);
        listTypelesson = getArguments().getParcelableArrayList(TYPELESSONS);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.dialog_rv_list, null);
        TextView title_dialog = view.findViewById(R.id.dialog_textView);
        title_dialog.setText("Тип занятия");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        rvTypelesson = view.findViewById(R.id.rv_dialog);
        listTypelessonAdapter = new ListTypelessonAdapter(listTypelesson, (position, view1) -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, clickedPosition);
            intent.putExtra(TYPELESSON_LIST, listTypelesson.get(position));
            getParentFragment().onActivityResult(TYPELESSON_CODE, Activity.RESULT_OK, intent);
            dismiss();
        });
        rvTypelesson.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        rvTypelesson.setAdapter(listTypelessonAdapter);

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
