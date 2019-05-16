package com.example.misha.myapplication.module.settings.theme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseAlertDialog;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.preferences.Preferences;

import org.jetbrains.annotations.NotNull;

import static com.example.misha.myapplication.Constants.SELECT_THEME;

public class DialogFragmentSelectTheme extends BaseAlertDialog {
    private DialogFragmentThemePresenter presenter;

    public static DialogFragmentSelectTheme newInstance() {
        return new DialogFragmentSelectTheme();
    }

    @NotNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        presenter = new DialogFragmentThemePresenter();
        presenter.init();

        String[] listItems = {"Темная", "Светлая"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Выберите тему");
        int checkedItem = 0;
        String nameTheme = Preferences.getInstance().getSelectedTheme();
        if (nameTheme.equals("DarkTheme")) {
            checkedItem = 0;
        }
        if (nameTheme.equals("LightTheme")) {
            checkedItem = 1;
        }



        builder.setSingleChoiceItems(listItems, checkedItem, (dialog, which) -> {
            if (which == 0) {
                Preferences.getInstance().setSelectedTheme("DarkTheme");
                getContext().setTheme(R.style.DarkTheme);
            }
            if (which == 1) {
                Preferences.getInstance().setSelectedTheme("LightTheme");
                getContext().setTheme(R.style.LightTheme);
            }
            Intent intent = getActivity().getIntent();
            getActivity().finish();
            startActivity(intent);
        });

        builder.setPositiveButton("Отмена", (dialog, which) -> dismiss());
        return builder.create();
    }


    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }
}
