package com.example.misha.myapplication.module.settings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.module.educator.SearchEducatorFragment;
import com.example.misha.myapplication.module.groups.GroupsFragment;
import com.example.misha.myapplication.module.audience.SearchAudienceFragment;
import com.example.misha.myapplication.module.settings.theme.DialogFragmentSelectTheme;
import com.example.misha.myapplication.module.transfer.TransferFragment;
import com.example.misha.myapplication.util.DataUtil;

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
        presenter = new SettingsPresenter();
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
        DataUtil.hintKeyboard(getContext());

        RelativeLayout layoutSearchEducator = view.findViewById(R.id.search_educator);
        RelativeLayout layoutSearchAudience = view.findViewById(R.id.search_audience);
        RelativeLayout layoutImport = view.findViewById(R.id.import_data);
        RelativeLayout layoutAbout = view.findViewById(R.id.about);
        RelativeLayout layoutSelectDate = view.findViewById(R.id.select_theme);
        RelativeLayout layoutTransferData = view.findViewById(R.id.transfer_data);
        ImageView imageSearchEducator = view.findViewById(R.id.image_searchEducator);
        ImageView imageSearchAudience = view.findViewById(R.id.image_searchAudience);
        ImageView imageImport = view.findViewById(R.id.image_import);
        ImageView imageTheme = view.findViewById(R.id.image_theme);
        ImageView imageAbout = view.findViewById(R.id.image_about);
        ImageView imageTransferData = view.findViewById(R.id.image_tranfer);
        ImageView imageArrowOne = view.findViewById(R.id.arrowOne);
        ImageView imageArrowTwo = view.findViewById(R.id.arrowTwo);
        ImageView imageArrowThree = view.findViewById(R.id.arrowThree);
        ImageView imageArrowFour = view.findViewById(R.id.arrowFour);
        ImageView imageArrowFive = view.findViewById(R.id.arrowFive);
        ImageView imageArrowSix = view.findViewById(R.id.arrowSix);
        if (Preferences.getInstance().getSelectedTheme().equals(DARK_THEME)) {
            imageSearchEducator.setImageResource(R.drawable.ic_school_white);
            imageSearchAudience.setImageResource(R.drawable.ic_search_white);
            imageImport.setImageResource(R.drawable.ic_import_white);
            imageTheme.setImageResource(R.drawable.ic_palette_white);
            imageAbout.setImageResource(R.drawable.ic_person_white);
            imageTransferData.setImageResource(R.drawable.ic_send_white);
            imageArrowOne.setImageResource(R.drawable.ic_arrow_white);
            imageArrowTwo.setImageResource(R.drawable.ic_arrow_white);
            imageArrowThree.setImageResource(R.drawable.ic_arrow_white);
            imageArrowFour.setImageResource(R.drawable.ic_arrow_white);
            imageArrowFive.setImageResource(R.drawable.ic_arrow_white);
            imageArrowSix.setImageResource(R.drawable.ic_arrow_white);
        }
        if (Preferences.getInstance().getSelectedTheme().equals(LIGHT_THEME)) {
            imageSearchEducator.setImageResource(R.drawable.ic_school_black);
            imageSearchAudience.setImageResource(R.drawable.ic_search_black);
            imageImport.setImageResource(R.drawable.ic_import);
            imageTheme.setImageResource(R.drawable.ic_palette_black);
            imageAbout.setImageResource(R.drawable.ic_person_black);
            imageTransferData.setImageResource(R.drawable.ic_send_black);
            imageArrowOne.setImageResource(R.drawable.ic_arrow_black);
            imageArrowTwo.setImageResource(R.drawable.ic_arrow_black);
            imageArrowThree.setImageResource(R.drawable.ic_arrow_black);
            imageArrowFour.setImageResource(R.drawable.ic_arrow_black);
            imageArrowFive.setImageResource(R.drawable.ic_arrow_black);
            imageArrowSix.setImageResource(R.drawable.ic_arrow_black);
        }
        layoutSearchEducator.setOnClickListener(this);
        layoutSearchAudience.setOnClickListener(this);
        layoutImport.setOnClickListener(this);
        layoutAbout.setOnClickListener(this);
        layoutSelectDate.setOnClickListener(this);
        layoutTransferData.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        inflater.inflate(R.menu.menu_main, menu);
    }


    public Dialog onCreateDialogAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton((R.string.string_vk_developer), (dialog, id) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.vk_developer)));
            startActivity(browserIntent);
        }).
                setNeutralButton(R.string.cancel, (dialog, id) -> dialog.cancel()).
                setNegativeButton((R.string.string_email_developer), (dialog, id) -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.email_developer)));
                    startActivity(browserIntent);
                }).setTitle(R.string.feedback_developer);
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

    public void openFragmentSearchAudience() {
        Fragment newFragment = new SearchAudienceFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openFragmentSearchEducator() {
        Fragment newFragment = new SearchEducatorFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openFragmentGroups() {
        Fragment newFragment = new GroupsFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openFragmentTransferData() {
        Fragment newFragment = new TransferFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_educator) {
            presenter.onSearchEducator();
        }
        if (v.getId() == R.id.search_audience) {
            presenter.onSearchAudience();
        }
        if (v.getId() == R.id.import_data) {
            presenter.onOpenFragmentGroups();
        }
        if (v.getId() == R.id.select_theme) {
            presenter.onCreateDialogSelectTheme();
        }
        if (v.getId() == R.id.transfer_data) {
            presenter.onOpenFragmentTransferData();
        }
        if (v.getId() == R.id.about) {
            presenter.onCreateDialogAbout();
        }

    }


}
