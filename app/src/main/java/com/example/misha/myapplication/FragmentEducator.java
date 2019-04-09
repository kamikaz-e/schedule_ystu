package com.example.misha.myapplication;

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

import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.entity.Educator;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;


public class FragmentEducator extends Fragment {

    EditText input_educator;
    ListView list_educators;
    ArrayList<Educator> educator_list = new ArrayList<>();
    public ArrayAdapter<Educator> adapter;

    public FragmentEducator() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_educator, container, false);
        input_educator = view.findViewById(R.id.input_educator);
        list_educators = view.findViewById(R.id.list_educators);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, educator_list);
        list_educators.setAdapter(adapter);
        list_educators.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                onCreateDialogDeleteItem(position).show();
            }
        });
        updateListView();
        input_educator.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))){
                    String educatorName = input_educator.getText().toString();
                    if(TextUtils.isEmpty(educatorName)) {
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
                }
                else{
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
                Educator educator = educator_list.get(position);
                EducatorDao.getInstance().deleteItemById(Long.parseLong(educator.getId()));
                updateListView();
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Удалить преподавателя «"+ educator_list.get(position)+"»?");
        return builder.create();
    }


    public void updateListView() {
        educator_list= EducatorDao.getInstance().getAllData();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, educator_list);
        list_educators.setAdapter(adapter);
    }

}
