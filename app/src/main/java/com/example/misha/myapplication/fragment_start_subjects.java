package com.example.misha.myapplication;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

import static android.content.Context.MODE_PRIVATE;


public class fragment_start_subjects extends android.support.v4.app.Fragment {


    EditText input_subject;
    ListView list_subjects;
    private ScheduleDB ScheduleDB;
    final ArrayList<String> subject_list = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    Button next;
    Button clear_subjects;
    String select_item="";

    public fragment_start_subjects() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_subjects, container, false);
        Toolbar profile_toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(profile_toolbar);
        ScheduleDB = new ScheduleDB(getActivity());

        clear_subjects= view.findViewById(R.id.clear_subjects);
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


        next= view.findViewById(R.id.next);
        clear_subjects.setBackgroundResource(R.drawable.ic_clear);
        next.setBackgroundResource(R.drawable.ic_start_settings_ok);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_start_audiences fragment= new fragment_start_audiences();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        clear_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialogClear().show();
            }
        });
        start();


            new MaterialTapTargetPrompt.Builder(getActivity())
                    .setTarget(input_subject)
                    .setPromptBackground(new RectanglePromptBackground())
                    .setPromptFocal(new RectanglePromptFocal())
                    .setPrimaryText("Добавление предметов")
                    .setSecondaryText("Введите предмет и добавьте его, нажав кнопку подтверждения на клавиатуре <Enter>")
                    .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200, 200, 255))
                    .setBackgroundColour(Color.rgb(100, 100, 255))
                    .setPrimaryTextColour(Color.rgb(255, 255, 255))
                    .setSecondaryTextColour(Color.rgb(255, 255, 255))
                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                            if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                                new MaterialTapTargetPrompt.Builder(getActivity())
                                        .setTarget(clear_subjects)
                                        .setPromptBackground(new RectanglePromptBackground())
                                        .setPromptFocal(new RectanglePromptFocal())
                                        .setPrimaryText("Удаление предметов")
                                        .setSecondaryText("Если необходимо удалить предметы, то нажмите на кнопку.\nТак же можно удалить нужный предмет, просто кликнув по нему в списке")
                                        .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200, 200, 255))
                                        .setBackgroundColour(Color.rgb(100, 100, 255))
                                        .setPrimaryTextColour(Color.rgb(255, 255, 255))
                                        .setSecondaryTextColour(Color.rgb(255, 255, 255))
                                        .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                                            public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                                                if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                                                    new MaterialTapTargetPrompt.Builder(getActivity())
                                                            .setTarget(next)
                                                            .setPromptBackground(new RectanglePromptBackground())
                                                            .setPromptFocal(new RectanglePromptFocal())
                                                            .setPrimaryText("Завершить добавление предметов")
                                                            .setSecondaryText("После добавления предметов нажмите на кнопку, чтобы перейти к следующим действиям")
                                                            .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200, 200, 255))
                                                            .setBackgroundColour(Color.rgb(100, 100, 255))
                                                            .setPrimaryTextColour(Color.rgb(255, 255, 255))
                                                            .setSecondaryTextColour(Color.rgb(255, 255, 255))
                                                            .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                                                                public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                                                                    if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {

                                                                    }
                                                                }
                                                            })
                                                            .show();
                                                }
                                            }
                                        })
                                        .show();
                            }
                        }
                    })
                    .show();

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
                return true;
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

    public Dialog onCreateDialogClear() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                clear_subjects();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить предметы?");
        return builder.create();
    }

    void clear_subjects () {
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        db.execSQL("DROP TABLE " + ScheduleClass.subjects.TABLE_NAME);
        db.execSQL("CREATE TABLE " + ScheduleClass.subjects.TABLE_NAME + " ("
                + ScheduleClass.subjects.idd_subject + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ScheduleClass.subjects.subject + " STRING UNIQUE ON CONFLICT IGNORE );");
        db.execSQL("INSERT INTO " + ScheduleClass.subjects.TABLE_NAME + " (" + ScheduleClass.subjects.subject + ") VALUES ('Предмет');");
        adapter.notifyDataSetChanged();
        start();
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
