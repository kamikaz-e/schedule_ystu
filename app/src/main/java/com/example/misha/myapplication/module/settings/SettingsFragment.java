package com.example.misha.myapplication.module.settings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.module.settings.theme.DialogFragmentSelectTheme;

import org.jetbrains.annotations.NotNull;

import static com.example.misha.myapplication.Constants.SELECT_THEME;
import static com.example.misha.myapplication.data.preferences.Preferences.DARK_THEME;
import static com.example.misha.myapplication.data.preferences.Preferences.LIGHT_THEME;


public class SettingsFragment extends BaseMainFragment implements SettingsFragmentView, View.OnClickListener {

    private SettingsPresenter presenter;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle(R.string.settings);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SettingsPresenter(getContext());
    }

    @NonNull
    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        RelativeLayout layoutPickWeek = view.findViewById(R.id.current_date);
        RelativeLayout layoutImport = view.findViewById(R.id.import_data);
        RelativeLayout layoutAbout = view.findViewById(R.id.about);
        RelativeLayout layoutSelectDate = view.findViewById(R.id.select_theme);
        ImageView imageSearchAudience = view.findViewById(R.id.image_searchAudience);
        ImageView imageImport = view.findViewById(R.id.image_import);
        ImageView imageTheme = view.findViewById(R.id.image_theme);
        ImageView imageAbout = view.findViewById(R.id.image_about);
        if (Preferences.getInstance().getSelectedTheme().equals(DARK_THEME)) {
            imageSearchAudience.setImageResource(R.drawable.ic_search_white);
            imageImport.setImageResource(R.drawable.ic_import_white);
            imageTheme.setImageResource(R.drawable.ic_palette_white);
            imageAbout.setImageResource(R.drawable.ic_person_white);
        }
        if (Preferences.getInstance().getSelectedTheme().equals(LIGHT_THEME)) {
            imageSearchAudience.setImageResource(R.drawable.ic_search_black);
            imageImport.setImageResource(R.drawable.ic_import);
            imageTheme.setImageResource(R.drawable.ic_palette_black);
            imageAbout.setImageResource(R.drawable.ic_person_black);
        }

        layoutPickWeek.setOnClickListener(this);
        layoutImport.setOnClickListener(this);
        layoutAbout.setOnClickListener(this);
        layoutSelectDate.setOnClickListener(this);
        return view;
    }


    public Dialog onCreateDialogImport() {
        LayoutInflater li = LayoutInflater.from(getContext());
        @SuppressLint("InflateParams") View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final EditText name_db = view.findViewById(R.id.name_schedule);
        builder.setCancelable(false).
                setPositiveButton("Загрузить", (dialog, id) -> presenter.loadSubjects(name_db.getText().toString())).
                setNegativeButton("Отмена", (dialog, id) -> {
                });
        return builder.create();
    }


    public Dialog onCreateDialogAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("Профиль Вконтакте", (dialog, id) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/mikhailvolkov1"));
            startActivity(browserIntent);
        }).
                setNeutralButton("Отмена", (dialog, id) -> dialog.cancel()).
                setNegativeButton("Электронная почта", (dialog, id) -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:mikhailvolkov2014-2014@ya.ru"));
                    startActivity(browserIntent);
                }).setTitle("Обратная связь с разработчиком");
        return builder.create();
    }

    public void showDialogSelectTheme() {
        DialogFragmentSelectTheme dialogFragment = DialogFragmentSelectTheme.newInstance();
        dialogFragment.show(getChildFragmentManager(), DialogFragmentSelectTheme.class.getSimpleName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultOk, Intent data) {
        if (requestCode == SELECT_THEME) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.current_date) {
            presenter.onDateClicked();
        }
        if (v.getId() == R.id.import_data) {
            presenter.onCreateDialogImport();
        }
        if (v.getId() == R.id.select_theme) {
            presenter.onCreateDialogSelectTheme();
        }
        if (v.getId() == R.id.about) {
            presenter.onCreateDialogAbout();
        }
    }


}
