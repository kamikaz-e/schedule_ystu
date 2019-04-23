package com.example.misha.myapplication.startFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.entity.Subject;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;


public class FragmentStartSubject extends Fragment {


    EditText input_subject;
    ListView list_subjects;

    ArrayList<Subject> subject_list = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    Button next;
    Button clear_subjects;
    int select_item;

    public FragmentStartSubject() {

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

        clear_subjects= view.findViewById(R.id.clearSubjects);
        input_subject = view.findViewById(R.id.inputSubject);
        list_subjects = view.findViewById(R.id.listSubjects);


        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, subject_list);
        list_subjects.setAdapter(adapter);

        list_subjects.setOnItemClickListener((parent, itemClicked, position, id) -> {
            TextView textView = (TextView) itemClicked;
            select_item = position;
            onCreateDialogDeleteItem(position).show();
        });


        next= view.findViewById(R.id.next);
        clear_subjects.setBackgroundResource(R.drawable.ic_clear);
        next.setBackgroundResource(R.drawable.ic_start_settings_ok);
        next.setOnClickListener(v -> {
            FragmentStartAudience fragment= new FragmentStartAudience();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        clear_subjects.setOnClickListener(v -> onCreateDialogClear().show());
        updateList();


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

        input_subject.setOnEditorActionListener((v, actionId, event) -> {
            if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))){
                input_subject.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                String subjectName = input_subject.getText().toString();
                subjectName = subjectName.trim().replaceAll(" +", " ");
                if(TextUtils.isEmpty(subjectName)|| subjectName ==" ") {
                    input_subject.setError("Введите предмет");
                    return true;
                }
                Subject subject = new Subject();
                subject.setName(subjectName);
                SubjectDao.getInstance().insertItem(subject);
                input_subject.getText().clear();
                updateList();
                adapter.notifyDataSetChanged();
                return true;
            }
            else{
                return false;
            }
        });
        return view;
    }
    public Dialog onCreateDialogDeleteItem(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                Subject subject = subject_list.get(position);
                SubjectDao.getInstance().deleteItemById(Long.parseLong(subject.getId()));
                updateList();
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
        SubjectDao.getInstance().deleteAll();
        adapter.notifyDataSetChanged();
        updateList();
    }

    public void updateList(){

       adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, subject_list);
        list_subjects.setAdapter(adapter);

        subject_list = SubjectDao.getInstance().getAllData();
        subject_list.clear();

    }

}
