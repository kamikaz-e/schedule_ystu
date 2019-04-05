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
import com.example.misha.myapplication.adapter.ListAudienceAdapter;
import com.example.misha.myapplication.adapter.SimpleItemClickListener;
import com.example.misha.myapplication.database.entity.Audience;

import java.util.ArrayList;


//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.
public class AudienceList extends DialogFragment {

    public static final int AUDIENCE_CODE = 3431;

    public static final String AUDIENCES = "AUDIENCES";
    public static final String AUDIENCE_LIST = "AUDIENCE_LIST";
    public static final String POSITION = "POSITION";

    private int clickedPosition;
    private ListAudienceAdapter listAudienceAdapter;
    private ArrayList<Audience> listAudience;
    private RecyclerView rvAudience;

    public static AudienceList newInstance(int position, ArrayList<Audience> audiences) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(AUDIENCES, audiences);
        args.putInt(POSITION, position);
        AudienceList fragment = new AudienceList();
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        clickedPosition = getArguments().getInt(POSITION);
        listAudience = getArguments().getParcelableArrayList(AUDIENCES);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.rv_list, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        rvAudience =  view.findViewById(R.id.rv);
        listAudienceAdapter =  new ListAudienceAdapter(listAudience, new SimpleItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Intent intent = new Intent();
                intent.putExtra(POSITION, clickedPosition);
                intent.putExtra(AUDIENCE_LIST, listAudience.get(position));
                getParentFragment().onActivityResult(AUDIENCE_CODE, Activity.RESULT_OK, intent);
                dismiss();
            }
        });
        rvAudience.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        rvAudience.setAdapter(listAudienceAdapter);

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
