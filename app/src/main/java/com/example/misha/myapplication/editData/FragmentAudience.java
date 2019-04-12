package com.example.misha.myapplication.editData;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.entity.Audience;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;


public class FragmentAudience extends Fragment {

    EditText input_audience;
    ListView list_audiences;

    ArrayList<Audience> audience_list = new ArrayList<>();
    public ArrayAdapter<String> adapter;

    public FragmentAudience() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audience, container, false);
        input_audience = view.findViewById(R.id.input_audience);
        list_audiences = view.findViewById(R.id.list_audiences);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, audience_list);
        list_audiences.setAdapter(adapter);
        list_audiences.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                onCreateDialogDeleteItem(position).show();
            }
        });
        updateListView();
        input_audience.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))){
                    input_audience.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                    String audienceName = input_audience.getText().toString();
                    if(TextUtils.isEmpty(audienceName)) {
                        input_audience.setError("Введите аудиторию");
                        return true;
                    }
                    Audience audience = new Audience();
                    audience.setName(audienceName);
                    AudienceDao.getInstance().insertItem(audience);
                    input_audience.getText().clear();
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
               Audience audience = audience_list.get(position);
                AudienceDao.getInstance().deleteItemById(Long.parseLong(audience.getId()));
                updateListView();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Удалить аудиторию «"+audience_list.get(position) +"»?");
        return builder.create();
    }

    public void updateListView() {
        audience_list = AudienceDao.getInstance().getAllData();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, audience_list);
        list_audiences.setAdapter(adapter);
    }

}
