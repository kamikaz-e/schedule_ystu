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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseAlertDialog;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.entity.SimpleItem;
import com.example.misha.myapplication.module.schedule.edit.page.EditSchedulePageFragmentView;
import com.example.misha.myapplication.module.schedule.edit.page.dialog.addData.DialogFragmentAddData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.misha.myapplication.Constants.ITEMS_LIST;

//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.

public class DialogFragmentListItems extends BaseAlertDialog implements DialogFragmentListItemsView {

    private DialogFragmentPresenter presenter;
    private RecyclerView rvItems;
    private DialogFragmentListItemsAdapter dialogFragmentListItemsAdapter;

    public static DialogFragmentListItems newInstance(Bundle args) {
        DialogFragmentListItems fragment = new DialogFragmentListItems();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void updateView(ArrayList<SimpleItem> listItems) {

    }

    @Override
    public void showAddDataDialog(ArrayList<? extends SimpleItem> items, int fragmentCode) {
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_CODE, fragmentCode);
        DialogFragmentAddData dialogFragment = DialogFragmentAddData.newInstance(args);
        dialogFragment.show(getChildFragmentManager(), DialogFragmentAddData.class.getSimpleName());
    }


    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int fragmentCode = getArguments().getInt(FRAGMENT_CODE);
        ArrayList<SimpleItem> listItems = getArguments().getParcelableArrayList(ITEMS);
        presenter = new DialogFragmentPresenter(fragmentCode);

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.dialog_rv_list, null);
        TextView title_dialog = view.findViewById(R.id.dialog_textView);
        if (fragmentCode == EditSchedulePageFragmentView.SUBJECT) {
            title_dialog.setText("Предметы");
        }
        if (fragmentCode == EditSchedulePageFragmentView.AUDIENCE) {
            title_dialog.setText("Аудитории");
        }
        if (fragmentCode == EditSchedulePageFragmentView.EDUCATOR) {
            title_dialog.setText("Преподаватели");
        }
        if (fragmentCode == EditSchedulePageFragmentView.TYPELESSON) {
            title_dialog.setText("Тип занятий");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        rvItems = view.findViewById(R.id.rv_dialog);
        updateItemsAdapter(listItems);
        Button button_add = view.findViewById(R.id.button_add);
        button_add.setOnClickListener(v -> presenter.onItemClick(fragmentCode));
        Button button_cancel = view.findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(v -> dismiss());
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultOk, Intent data) {
        ArrayList<SimpleItem> subjectList = presenter.getSubjectList();
        dialogFragmentListItemsAdapter.setLessonList(subjectList);
        dialogFragmentListItemsAdapter.notifyDataSetChanged();
        updateItemsAdapter(subjectList);

    }


    public void updateItemsAdapter(ArrayList<SimpleItem> subjectList) {
        int fragmentCode = getArguments().getInt(FRAGMENT_CODE);
        int clickedPosition = getArguments().getInt(POSITION);

        dialogFragmentListItemsAdapter = new DialogFragmentListItemsAdapter(subjectList, (position, view1) -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, clickedPosition);
            intent.putExtra(ITEMS_LIST, subjectList.get(position));
            DialogFragmentListItems.this.getParentFragment().onActivityResult(fragmentCode, Activity.RESULT_OK, intent);
            DialogFragmentListItems.this.dismiss();
        });
        rvItems.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvItems.setAdapter(dialogFragmentListItemsAdapter);
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }
}
