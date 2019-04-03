package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;


public class FragmentEducator extends android.support.v4.app.Fragment {


    EditText input_educator;
    ListView list_educators;
    private ScheduleDB ScheduleDB;
    final ArrayList<String> educator_list = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    String select_item="";

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

        ScheduleDB = new ScheduleDB();

        input_educator = view.findViewById(R.id.input_educator);
        list_educators = view.findViewById(R.id.list_educators);

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, educator_list);
        list_educators.setAdapter(adapter);

        list_educators.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                select_item = textView.getText().toString();
                onCreateDialogDeleteItem().show();
            }
        });

        start();

        input_educator.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))){
                    String educator = input_educator.getText().toString();
                    if(TextUtils.isEmpty(educator)) {
                        input_educator.setError("Введите преподавателя");
                        return true;
                    }
                    SQLiteDatabase db = ScheduleDB.getWritableDatabase();
                    db.execSQL("INSERT INTO " + ScheduleClass.educators.TABLE_NAME + " (" + ScheduleClass.educators.educator + ") VALUES ('" + educator + "');");
                    input_educator.setText("");
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
                db.execSQL("DELETE FROM " + ScheduleClass.educators.TABLE_NAME + " WHERE "+ ScheduleClass.educators.educator + "='"+ select_item+"'");
                start();
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Удалить преподавателя «"+select_item+"»?");
        return builder.create();
    }


    public void start(){

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, educator_list);
        list_educators.setAdapter(adapter);

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT "+ ScheduleClass.educators.educator +" FROM " + ScheduleClass.educators.TABLE_NAME + " WHERE "+ ScheduleClass.educators.idd_educator +">1;";
        educator_list.clear();
        Cursor cursor = db.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
            educator_list.add(cursor.getString(0));
        }
        cursor.close();
    }

}
