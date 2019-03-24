package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;

import java.util.ArrayList;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_start_educators.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_start_educators#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_start_educators extends android.support.v4.app.Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private OnFragmentInteractionListener mListener;

    EditText input_educator;
    ListView list_educators;
    private ScheduleDB ScheduleDB;
    final ArrayList<String> educator_list = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    Button next;
    Button clear_educators;


    public fragment_start_educators() {
        // Required empty public constructor
    }


    public static fragment_start_educators newInstance(String param1, String param2) {
        fragment_start_educators fragment = new fragment_start_educators();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_educators, container, false);
        Toolbar profile_toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(profile_toolbar);
        ScheduleDB = new ScheduleDB(getActivity());
        next= view.findViewById(R.id.next);
        clear_educators= view.findViewById(R.id.clear_educators);
        input_educator = view.findViewById(R.id.input_educator);
        list_educators = view.findViewById(R.id.list_educators);
        clear_educators.setBackgroundResource(R.drawable.ic_clear);
        next.setBackgroundResource(R.drawable.ic_start_settings_ok);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        clear_educators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialogClear().show();
            }
        });
        start();

        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(input_educator)
                .setPromptBackground(new RectanglePromptBackground())
                .setPromptFocal(new RectanglePromptFocal())
                .setPrimaryText("Добавление преподавателей")
                .setSecondaryText("Введите ФИО преподавателя и добавьте его, нажав кнопку подтверждения на клавиатуре <Enter>")
                .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200, 200, 255))
                .setBackgroundColour(Color.rgb(100, 100, 255))
                .setPrimaryTextColour(Color.rgb(255, 255, 255))
                .setSecondaryTextColour(Color.rgb(255, 255, 255))
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                        if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {
                            new MaterialTapTargetPrompt.Builder(getActivity())
                                    .setTarget(clear_educators)
                                    .setPromptBackground(new RectanglePromptBackground())
                                    .setPromptFocal(new RectanglePromptFocal())
                                    .setPrimaryText("Удаление преподавателей")
                                    .setSecondaryText("Если необходимо удалить преподавателей, то нажмите на кнопку")
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
                                                        .setPrimaryText("Завершить добавление преподавателей")
                                                        .setSecondaryText("После добавления преподавателей нажмите на кнопку, чтобы перейти к следующим действиям")
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


        input_educator.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        String educator = input_educator.getText().toString();
                        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
                        db.execSQL("INSERT INTO " + ScheduleClass.educators.TABLE_NAME + " (" + ScheduleClass.educators.educator + ") VALUES ('" + educator + "');");
                        input_educator.setText("");
                        start();
                        adapter.notifyDataSetChanged();
                        return true;
                    }
                return true;
            }
        });
        return view;
    }

    public Dialog onCreateDialogClear() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                clear_educators();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить преподавателей?");
        return builder.create();
    }

    void clear_educators(){
        SQLiteDatabase db = ScheduleDB.getWritableDatabase();
        db.execSQL("DROP TABLE " + ScheduleClass.educators.TABLE_NAME);
        db.execSQL("CREATE TABLE " + ScheduleClass.educators.TABLE_NAME + " ("
                + ScheduleClass.educators.idd_educator + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ScheduleClass.educators.educator + " STRING UNIQUE ON CONFLICT IGNORE );");
        db.execSQL("INSERT INTO " + ScheduleClass.educators.TABLE_NAME + " (" + ScheduleClass.educators.educator + ") VALUES ('Преподаватель');");
        adapter.notifyDataSetChanged();
        start();
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
