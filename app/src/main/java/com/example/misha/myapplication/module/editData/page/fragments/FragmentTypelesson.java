package com.example.misha.myapplication.module.editData.page.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.data.database.dao.TypelessonDao;
import com.example.misha.myapplication.data.database.entity.Typelesson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class FragmentTypelesson extends Fragment {

    private EditText inputTypelesson;
    private ListView typelessonListView;
    private ArrayList<Typelesson> typelessonList = new ArrayList<>();
    public ArrayAdapter<Typelesson> adapter;

    public FragmentTypelesson() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_typelesson, container, false);
        inputTypelesson = view.findViewById(R.id.input_typelesson);
        typelessonListView = view.findViewById(R.id.list_typelesson);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, typelessonList);
        typelessonListView.setAdapter(adapter);
        typelessonListView.setOnItemClickListener((parent, itemClicked, position, id) -> onCreateDialogDeleteItem(position).show());
        updateListView();
        inputTypelesson.setOnEditorActionListener((v, actionId, event) -> {
            if ((actionId == EditorInfo.IME_ACTION_DONE) ||
                    ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
                String typelessonName = inputTypelesson.getText().toString();
                if (TextUtils.isEmpty(typelessonName)) {
                    inputTypelesson.setError("Введите тип занятия");
                    return true;
                }
                Typelesson typelesson = new Typelesson();
                typelesson.setName(typelessonName);
                TypelessonDao.getInstance().insertItem(typelesson);
                inputTypelesson.getText().clear();
                updateListView();
                adapter.notifyDataSetChanged();
                return true;
            } else {
                return false;
            }
        });
        return view;
    }

    private Dialog onCreateDialogDeleteItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) -> {
            Typelesson typelesson = typelessonList.get(position);
            TypelessonDao.getInstance().deleteItemById(Long.parseLong(typelesson.getId()));
            updateListView();
            adapter.notifyDataSetChanged();
        }).setNegativeButton("Отмена", (dialog, id) ->
                dialog.cancel()).setTitle("Удалить тип занятия «" + typelessonList.get(position) + "»?");
        return builder.create();
    }


    private void updateListView() {
        typelessonList = TypelessonDao.getInstance().getAllData();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, typelessonList);
        typelessonListView.setAdapter(adapter);
    }

}
