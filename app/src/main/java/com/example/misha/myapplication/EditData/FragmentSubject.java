package com.example.misha.myapplication.EditData;

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
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.entity.Lesson;
import com.example.misha.myapplication.database.entity.Subject;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public class FragmentSubject extends Fragment {


    EditText input_subject;
    ListView list_subjects;
    ArrayList<Subject> subject_list = new ArrayList<>();
    ArrayList<Lesson> lessonList = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    ViewPager viewpager;

    public FragmentSubject() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        input_subject = view.findViewById(R.id.input_subject);
        list_subjects = view.findViewById(R.id.list_subjects);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, subject_list);
        list_subjects.setAdapter(adapter);
        list_subjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                onCreateDialogDeleteItem(position).show();
            }
        });
        updateListView();

        viewpager = getActivity().findViewById(R.id.viewPager); //добавить подсказку

        input_subject.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
                    input_subject.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                    String subjectName = input_subject.getText().toString();
                    subjectName = subjectName.trim().replaceAll(" +", " ");
                    if (TextUtils.isEmpty(subjectName) || subjectName == " ") {
                        input_subject.setError("Введите предмет");
                        return true;
                    }
                    Subject subject = new Subject();
                    subject.setName(subjectName);
                    SubjectDao.getInstance().insertItem(subject);
                    input_subject.getText().clear();
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
                Subject subject = subject_list.get(position);
                SubjectDao.getInstance().deleteItemById(Long.parseLong(subject.getId()));
                updateListView();
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Удалить предмет «" + subject_list.get(position) + "»?");
        return builder.create();
    }

    public void updateListView() {
        subject_list = SubjectDao.getInstance().getAllData();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, subject_list);
        list_subjects.setAdapter(adapter);
    }
}
