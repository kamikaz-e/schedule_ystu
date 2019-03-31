package com.example.misha.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

import static android.content.Context.MODE_PRIVATE;


public class fragment_subject extends android.support.v4.app.Fragment {


    EditText input_subject;
    ListView list_subjects;
    private ScheduleDB ScheduleDB;
    final ArrayList<String> subject_list = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    String select_item="";
    ViewPager vp;
    Button clear_subjects;
    Integer abc=0;
    public fragment_subject() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_subject, container, false);

        ScheduleDB = new ScheduleDB(getActivity());

        input_subject = view.findViewById(R.id.input_subject);
        list_subjects = view.findViewById(R.id.list_subjects);


        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, subject_list);
        list_subjects.setAdapter(adapter);

        list_subjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                select_item = textView.getText().toString();
                onCreateDialogDeleteItem().show();
            }
        });

        start();

        vp = getActivity().findViewById(R.id.viewPager); //добавить подсказку


        InputFilter filter = new InputFilter() {
            boolean canEnterSpace = false;

            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {

                if(input_subject.getText().toString().equals(""))
                {
                    canEnterSpace = false;
                }

                StringBuilder builder = new StringBuilder();

                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);

                    if (Character.isLetterOrDigit(currentChar)) {
                        builder.append(currentChar);
                        canEnterSpace = true;
                    }

                    if(Character.isWhitespace(currentChar) && canEnterSpace) {
                        builder.append(currentChar);
                    }


                }
                return builder.toString();
            }

        };

        input_subject.setFilters(new InputFilter[]{filter});
        input_subject.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    input_subject.requestFocus();
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {

                        String subject = input_subject.getText().toString();
                        if(TextUtils.isEmpty(subject)) {
                            input_subject.setError("Введите предмет");
                            return true;
                        }
                        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
                        db.execSQL("INSERT INTO " + ScheduleClass.subjects.TABLE_NAME + " (" + ScheduleClass.subjects.subject + ") VALUES ('" + subject + "');");
                        input_subject.getText().clear();
                        start();
                        adapter.notifyDataSetChanged();
                        return true;

                    }
                return false;
            }
        });
        TabLayout tabLayout = getActivity().findViewById(R.id.tabs);
        abc=tabLayout.getSelectedTabPosition();

        return view;
    }
    public Dialog onCreateDialogDeleteItem() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                SQLiteDatabase db = ScheduleDB.getWritableDatabase();
                db.execSQL("DELETE FROM " + ScheduleClass.subjects.TABLE_NAME + " WHERE "+ ScheduleClass.subjects.subject + "='"+ select_item+"'");
                start();
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Удалить предмет «"+select_item+"»?");
        return builder.create();
    }



    public void start(){

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, subject_list);
        list_subjects.setAdapter(adapter);

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT "+ ScheduleClass.subjects.subject +" FROM " + ScheduleClass.subjects.TABLE_NAME + " WHERE "+ ScheduleClass.subjects.idd_subject +">1;";
        subject_list.clear();
        Cursor cursor = db.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
            subject_list.add(cursor.getString(0));
        }
        cursor.close();
    }



}
