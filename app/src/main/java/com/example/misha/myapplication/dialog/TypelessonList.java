package com.example.misha.myapplication.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.misha.myapplication.ActivityEditData;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.adapter.ListTypelessonAdapter;
import com.example.misha.myapplication.adapter.SimpleItemClickListener;
import com.example.misha.myapplication.database.entity.Typelesson;

import java.util.ArrayList;


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

        View view = layoutInflater.inflate(R.layout.rv_list, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        rvTypelesson =  view.findViewById(R.id.rv);
        listTypelessonAdapter =  new ListTypelessonAdapter(listTypelesson, new SimpleItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Intent intent = new Intent();
                intent.putExtra(POSITION, clickedPosition);
                intent.putExtra(TYPELESSON_LIST, listTypelesson.get(position));
                getParentFragment().onActivityResult(TYPELESSON_CODE, Activity.RESULT_OK, intent);
                dismiss();
            }
        });
        rvTypelesson.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        rvTypelesson.setAdapter(listTypelessonAdapter);

        Button button_add = view.findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityEditData.class);
                getActivity().finish();
                getActivity().startActivity(intent);
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
