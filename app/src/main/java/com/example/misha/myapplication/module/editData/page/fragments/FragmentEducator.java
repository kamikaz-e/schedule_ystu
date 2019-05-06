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
import com.example.misha.myapplication.data.database.dao.EducatorDao;
import com.example.misha.myapplication.data.database.entity.Educator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class FragmentEducator extends Fragment {

    private EditText input_educator;
    private ListView list_educators;

    private ArrayList<Educator> educator_list = new ArrayList<>();
    public ArrayAdapter<Educator> adapter;

    public FragmentEducator() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_educator, container, false);
        input_educator = view.findViewById(R.id.input_educators);
        list_educators = view.findViewById(R.id.list_educators);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, educator_list);
        list_educators.setAdapter(adapter);
        list_educators.setOnItemClickListener((parent, itemClicked, position, id) -> onCreateDialogDeleteItem(position).show());
        updateListView();
        input_educator.setOnEditorActionListener((v, actionId, event) -> {
            if ((actionId == EditorInfo.IME_ACTION_DONE) ||
                    ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
                String educatorName = input_educator.getText().toString();
                if (TextUtils.isEmpty(educatorName)) {
                    input_educator.setError("Введите преподавателя");
                    return true;
                }
                Educator educator = new Educator();
                educator.setName(educatorName);
                EducatorDao.getInstance().insertItem(educator);
                input_educator.getText().clear();
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
            Educator educator = educator_list.get(position);
            EducatorDao.getInstance().deleteItemById(Long.parseLong(educator.getId()));
            updateListView();
            adapter.notifyDataSetChanged();
        }).setNegativeButton("Отмена", (dialog, id) ->
                dialog.cancel()).setTitle("Удалить преподавателя «" + educator_list.get(position) + "»?");
        return builder.create();
    }


    private void updateListView() {
        educator_list = EducatorDao.getInstance().getAllData();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, educator_list);
        list_educators.setAdapter(adapter);
    }

}
