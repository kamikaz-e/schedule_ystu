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
import com.example.misha.myapplication.module.schedule.edit.page.editScheduleListAdapters.ListEducatorAdapter;
import com.example.misha.myapplication.data.database.entity.Educator;
import com.example.misha.myapplication.module.editData.EditData;

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

        View view = layoutInflater.inflate(R.layout.dialog_rv_list, null);
        TextView title_dialog = view.findViewById(R.id.dialog_textView);
        title_dialog.setText("Преподаватель");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        rvEducatorR = view.findViewById(R.id.rv_dialog);
        listEDUCATORAdapter = new ListEducatorAdapter(listEducator, (position, view1) -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, clickedPosition);
            intent.putExtra(EDUCATOR_LIST, listEducator.get(position));
            getParentFragment().onActivityResult(EDUCATOR_CODE, Activity.RESULT_OK, intent);
            dismiss();
        });
        rvEducatorR.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        rvEducatorR.setAdapter(listEDUCATORAdapter);

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