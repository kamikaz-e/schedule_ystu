package com.example.misha.myapplication.dialog;

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
import com.example.misha.myapplication.adapter.editScheduleListAdapters.ListAudienceAdapter;
import com.example.misha.myapplication.database.entity.Audience;

import java.util.ArrayList;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

        View view = layoutInflater.inflate(R.layout.dialog_rv_list, null);
        View layoutTitleDialog = layoutInflater.inflate(R.layout.title_dialog, null);
        TextView title_dialog = layoutTitleDialog.findViewById(R.id.textViewDialog);
        title_dialog.setText("Аудитория");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        builder.setCustomTitle(layoutTitleDialog);
        rvAudience = view.findViewById(R.id.rvDialog);
        listAudienceAdapter = new ListAudienceAdapter(listAudience, (position, view1) -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, clickedPosition);
            intent.putExtra(AUDIENCE_LIST, listAudience.get(position));
            getParentFragment().onActivityResult(AUDIENCE_CODE, Activity.RESULT_OK, intent);
            dismiss();
        });
        rvAudience.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvAudience.setAdapter(listAudienceAdapter);
        Button button_add = view.findViewById(R.id.buttonAdd);
        button_add.setOnClickListener(v -> {

        });
        Button button_cancel = view.findViewById(R.id.buttonCancel);
        button_cancel.setOnClickListener(v -> dismiss());
        return builder.create();
    }

}
