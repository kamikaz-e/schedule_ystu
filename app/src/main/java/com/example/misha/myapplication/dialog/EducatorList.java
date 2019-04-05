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
import com.example.misha.myapplication.adapter.ListEducatorAdapter;
import com.example.misha.myapplication.adapter.SimpleItemClickListener;
import com.example.misha.myapplication.database.entity.Educator;

import java.util.ArrayList;


//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.
public class EducatorList extends DialogFragment {

    public static final int EDUCATOR_CODE = 3430;

    public static final String EDUCATORS = "EDUCATORS";
    public static final String EDUCATOR_LIST = "EDUCATOR_LIST";
    public static final String POSITION = "POSITION";

    private int clickedPosition;
    private ListEducatorAdapter listEDUCATORAdapter;
    private ArrayList<Educator> listEducator;
    private RecyclerView rvEducatorR;

    public static EducatorList newInstance(int position, ArrayList<Educator> Educators) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(EDUCATORS, Educators);
        args.putInt(POSITION, position);
        EducatorList fragment = new EducatorList();
        fragment.setArguments(args);
        return fragment;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        clickedPosition = getArguments().getInt(POSITION);
        listEducator = getArguments().getParcelableArrayList(EDUCATORS);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.rv_list, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        rvEducatorR =  view.findViewById(R.id.rv);
        listEDUCATORAdapter =  new ListEducatorAdapter(listEducator, new SimpleItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Intent intent = new Intent();
                intent.putExtra(POSITION, clickedPosition);
                intent.putExtra(EDUCATOR_LIST, listEducator.get(position));
                getParentFragment().onActivityResult(EDUCATOR_CODE, Activity.RESULT_OK, intent);
                dismiss();
            }
        });
        rvEducatorR.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        rvEducatorR.setAdapter(listEDUCATORAdapter);

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
