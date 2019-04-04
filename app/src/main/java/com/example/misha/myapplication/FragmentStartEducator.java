package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;

import static com.example.misha.myapplication.data.ScheduleClass.educators.EDUCATOR;
import static com.example.misha.myapplication.data.ScheduleClass.educators.educator;
import static com.example.misha.myapplication.data.ScheduleClass.educators.educator_id;

public class FragmentStartEducator extends android.support.v4.app.Fragment {

    EditText input_educator;
    ListView list_educators;
    private ScheduleDB ScheduleDB;
    final ArrayList<String> educator_list = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    Button next;
    Button clear_educators;
    String select_item="";

    public FragmentStartEducator() {

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
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(profile_toolbar);
        ScheduleDB = new ScheduleDB();
        next = view.findViewById(R.id.next);
        clear_educators = view.findViewById(R.id.clear_educators);
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
                .setSecondaryText("Аналогично, как и в предыдущем действии")
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
                    db.execSQL("INSERT INTO " + EDUCATOR + " (" + ScheduleClass.educators.educator + ") VALUES ('" + educator + "');");
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
                db.execSQL("DELETE FROM " + EDUCATOR + " WHERE "+ ScheduleClass.educators.educator + "='"+ select_item+"'");
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
        db.execSQL("DROP TABLE " + EDUCATOR);
        db.execSQL("CREATE TABLE " + EDUCATOR + " ("
                + educator_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + educator + " STRING UNIQUE ON CONFLICT IGNORE );");
        db.execSQL("INSERT INTO " + EDUCATOR + " (" + educator+ ") VALUES ('Преподаватель');");
        adapter.notifyDataSetChanged();
        start();
    }

    public void start(){

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, educator_list);
        list_educators.setAdapter(adapter);

        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "SELECT "+ educator +" FROM " + EDUCATOR + " WHERE "+ educator_id +">1;";
        educator_list.clear();
        Cursor cursor = db.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
            educator_list.add(cursor.getString(0));
        }
        cursor.close();
    }
}
