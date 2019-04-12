package com.example.misha.myapplication.editData;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.dao.TypelessonDao;
import com.example.misha.myapplication.database.entity.Typelesson;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;


public class FragmentTypelesson extends Fragment {

    EditText inputTypelesson;
    ListView typelessonListView;
    ArrayList<Typelesson> typelessonList = new ArrayList<>();
    public ArrayAdapter<Typelesson> adapter;

    public FragmentTypelesson() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_typelesson, container, false);
        inputTypelesson = view.findViewById(R.id.input_typelesson);
        typelessonListView = view.findViewById(R.id.list_typelesson);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, typelessonList);
        typelessonListView.setAdapter(adapter);
        typelessonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                onCreateDialogDeleteItem(position).show();
            }
        });
        updateListView();
        inputTypelesson.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
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
            }
        });
        return view;
    }

    public Dialog onCreateDialogDeleteItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Typelesson typelesson = typelessonList.get(position);
                TypelessonDao.getInstance().deleteItemById(Long.parseLong(typelesson.getId()));
                updateListView();
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Удалить тип занятия «" + typelessonList.get(position) + "»?");
        return builder.create();
    }


    public void updateListView() {
        typelessonList = TypelessonDao.getInstance().getAllData();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, typelessonList);
        typelessonListView.setAdapter(adapter);
    }

}
