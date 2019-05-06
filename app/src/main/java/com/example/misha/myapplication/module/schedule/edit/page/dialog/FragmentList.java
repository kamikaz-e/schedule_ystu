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
import com.example.misha.myapplication.module.editData.EditData;
import com.example.misha.myapplication.module.schedule.edit.page.editScheduleListAdapters.ListAudienceAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.
public class FragmentList extends DialogFragment {

    public static final int FRAGMENT_CODE = 0;
    public static final String SUBJECT = "4440";
    public static final String AUDIENCE = "2220";
    public static final String EDUCATOR = "1110";
    public static final String TYPELESSON = "3330";

    public static final String ITEMS = "ITEMS";

    public static final String ITEMS_LIST = "ITEMS_LIST";
    public static final String POSITION = "POSITION";


    private int clickedPosition;
    private ArrayList<SimpleItem> listAudience;

    public static FragmentList newInstance(int position, ArrayList<SimpleItem> item) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ITEMS, item);
        args.putInt(POSITION, position);
        FragmentList fragment = new FragmentList();
        fragment.setArguments(args);
        return fragment;
    }

    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        clickedPosition = getArguments().getInt(POSITION);
        listAudience = getArguments().getParcelableArrayList(ITEMS);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.dialog_rv_list, null);
        TextView title_dialog = view.findViewById(R.id.dialog_textView);
        title_dialog.setText("Что-то");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        RecyclerView rvAudience = view.findViewById(R.id.rv_dialog);
        ListAudienceAdapter listAudienceAdapter = new ListAudienceAdapter(listAudience, (position, view1) -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, clickedPosition);
            intent.putExtra(ITEMS_LIST, listAudience.get(position));
            getParentFragment().onActivityResult(FRAGMENT_CODE, Activity.RESULT_OK, intent);
            dismiss();
        });
        rvAudience.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvAudience.setAdapter(listAudienceAdapter);
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
