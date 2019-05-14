package com.example.misha.myapplication.module.schedule.edit.page.dialog.addData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseAlertDialog;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.module.schedule.edit.page.EditSchedulePageFragmentView;

import org.jetbrains.annotations.NotNull;

import static com.example.misha.myapplication.Constants.FRAGMENT_CODE;
import static com.example.misha.myapplication.data.preferences.Preferences.FRAGMENT_AUDIENCES;
import static com.example.misha.myapplication.data.preferences.Preferences.FRAGMENT_EDUCATORS;
import static com.example.misha.myapplication.data.preferences.Preferences.FRAGMENT_SUBJECTS;
import static com.example.misha.myapplication.data.preferences.Preferences.FRAGMENT_TYPELESSONS;

public class DialogFragmentAddData extends BaseAlertDialog  implements  TextView.OnEditorActionListener{

    private DialogFragmentDataPresenter presenter;
    private EditText inputItem;

    public static DialogFragmentAddData newInstance(Bundle args) {
        DialogFragmentAddData fragment = new DialogFragmentAddData();
        fragment.setArguments(args);
        return fragment;
    }

    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        int fragmentCode = getArguments().getInt(FRAGMENT_CODE);
        presenter = new DialogFragmentDataPresenter(fragmentCode);
        presenter.init();

        @SuppressLint("InflateParams")
        View view = layoutInflater.inflate(R.layout.dialog_add_data, null);
        TextView title_dialog = view.findViewById(R.id.dialog_textView);
        inputItem = view.findViewById(R.id.dialog_editText);
        inputItem.setOnEditorActionListener(this);
        if (fragmentCode == EditSchedulePageFragmentView.SUBJECT) {
            title_dialog.setText("Добавить предмет");
            inputItem.setHint("Предмет");
            inputItem.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});
        }
        if (fragmentCode == EditSchedulePageFragmentView.AUDIENCE) {
            title_dialog.setText("Добавить аудиторию");
            inputItem.setHint("Аудитория");
            inputItem.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
        }
        if (fragmentCode == EditSchedulePageFragmentView.EDUCATOR) {
            title_dialog.setText("Добавить преподавателя");
            inputItem.setHint("Преподаватель");
            inputItem.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});
        }
        if (fragmentCode == EditSchedulePageFragmentView.TYPELESSON) {
            title_dialog.setText("Добавить тип занятия");
            inputItem.setHint("Тип занятия");
            inputItem.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setView(view);

        Button button_cancel = view.findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(v -> {
            Intent intent = new Intent();
            getParentFragment().onActivityResult(fragmentCode, Activity.RESULT_OK, intent);
            dismiss();
        });
        return builder.create();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        int fragmentCode = getArguments().getInt(FRAGMENT_CODE);
        if ((actionId == EditorInfo.IME_ACTION_DONE) ||
                ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
            inputItem.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            String itemName = inputItem.getText().toString();
            itemName = itemName.trim().replaceAll(" +", " ");
            if (TextUtils.isEmpty(itemName) || itemName.equals(" ")) {
                if (fragmentCode == EditSchedulePageFragmentView.SUBJECT) {inputItem.setError("Введите предмет");}
                if (fragmentCode == EditSchedulePageFragmentView.AUDIENCE) {inputItem.setError("Введите аудиторию");}
                if (fragmentCode == EditSchedulePageFragmentView.EDUCATOR) {inputItem.setError("Введите преподавателя");}
                if (fragmentCode == EditSchedulePageFragmentView.TYPELESSON) {inputItem.setError("Введите тип предмета");}
                return true;
            }
            presenter.insert(itemName);
            inputItem.getText().clear();
            return true;
        } else {
            return false;
        }
    }


    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }
}
