package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;


public class FragmentAudience extends android.support.v4.app.Fragment {


    EditText input_audience;
    ListView list_audiences;
    private ScheduleDB ScheduleDB;
    final ArrayList<String> audience_list = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    Button clear_audiences;
    Button next;
    String select_item="";
    Integer abc=0;

    public FragmentAudience() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audience, container, false);

        ScheduleDB = new ScheduleDB(getActivity());

        input_audience = view.findViewById(R.id.input_audience);
        list_audiences = view.findViewById(R.id.list_audiences);


        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, audience_list);
        list_audiences.setAdapter(adapter);

        list_audiences.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                select_item = textView.getText().toString();
                onCreateDialogDeleteItem().show();
            }
        });

        start();


        input_audience.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))){
                    input_audience.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                    String audience = input_audience.getText().toString();
                    if(TextUtils.isEmpty(audience)) {
                        input_audience.setError("Введите аудиторию");
                        return true;
                    }
                    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
                    db.execSQL("INSERT INTO " + ScheduleClass.audiences.TABLE_NAME + " (" + ScheduleClass.audiences.audience + ") VALUES ('" + audience + "');");
                    input_audience.setText("");
                    start();
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

    public Dialog onCreateDialogDeleteItem() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                SQLiteDatabase db = ScheduleDB.getWritableDatabase();
                db.execSQL("DELETE FROM " + ScheduleClass.audiences.TABLE_NAME + " WHERE "+ ScheduleClass.audiences.audience + "='"+ select_item+"'");
                start();
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Удалить аудиторию «"+select_item+"»?");
        return builder.create();
    }



    public void start(){

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, audience_list);
        list_audiences.setAdapter(adapter);

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT "+ ScheduleClass.audiences.audience +" FROM " + ScheduleClass.audiences.TABLE_NAME + " WHERE "+ ScheduleClass.audiences.idd_audience +">1;";
        audience_list.clear();
        Cursor cursor = db.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
            audience_list.add(cursor.getString(0));
        }
        cursor.close();
    }


}
