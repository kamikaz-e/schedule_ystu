package com.example.misha.myapplication.editData;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.entity.Subject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class FragmentSubject extends Fragment {


    private EditText input_subject;
    private ListView list_subjects;
    private ArrayList<Subject> subject_list = new ArrayList<>();

    public ArrayAdapter<Subject> adapter;

    public FragmentSubject() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        input_subject = view.findViewById(R.id.input_subject);
        list_subjects = view.findViewById(R.id.list_subjects);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, subject_list);
        list_subjects.setAdapter(adapter);
        list_subjects.setOnItemClickListener((parent, itemClicked, position, id) -> onCreateDialogDeleteItem(position).show());
        updateListView();

        input_subject.setOnEditorActionListener((v, actionId, event) -> {
            if ((actionId == EditorInfo.IME_ACTION_DONE) ||
                    ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
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
        });
        return view;
    }

    private Dialog onCreateDialogDeleteItem(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", (dialog, id) -> {
            Subject subject = subject_list.get(position);
            SubjectDao.getInstance().deleteItemById(Long.parseLong(subject.getId()));
            updateListView();
            adapter.notifyDataSetChanged();
        }).setNegativeButton("Отмена", (dialog, id) ->
                dialog.cancel()).setTitle("Удалить предмет «" + subject_list.get(position) + "»?");
        return builder.create();
    }

    private void updateListView() {
        subject_list = SubjectDao.getInstance().getAllData();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, subject_list);
        list_subjects.setAdapter(adapter);
    }
}
