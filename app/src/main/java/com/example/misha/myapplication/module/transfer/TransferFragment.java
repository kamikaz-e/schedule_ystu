package com.example.misha.myapplication.module.transfer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.preferences.Preferences;

import org.jetbrains.annotations.NotNull;

import static com.example.misha.myapplication.data.preferences.Preferences.DARK_THEME;
import static com.example.misha.myapplication.data.preferences.Preferences.LIGHT_THEME;

public class TransferFragment extends BaseMainFragment implements TransferFragmentView, View.OnClickListener {

    private TransferPresenter presenter;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle(R.string.title_transfer_data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TransferPresenter(getActivity());
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer_data, container, false);
        RelativeLayout layoutImport = view.findViewById(R.id.rel_import);
        RelativeLayout layoutExport = view.findViewById(R.id.rel_export);
        ImageView imageImport = view.findViewById(R.id.image_import);
        ImageView imageExport = view.findViewById(R.id.image_export);
        if (Preferences.getInstance().getSelectedTheme().equals(DARK_THEME)) {
            imageImport.setImageResource(R.drawable.ic_unarchive_white);
            imageExport.setImageResource(R.drawable.ic_archive_white);
        }
        if (Preferences.getInstance().getSelectedTheme().equals(LIGHT_THEME)) {
            imageImport.setImageResource(R.drawable.ic_unarchive_black);
            imageExport.setImageResource(R.drawable.ic_archive_black);
        }

        layoutImport.setOnClickListener(this);
        layoutExport.setOnClickListener(this);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.init();
    }

    @NonNull
    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rel_import) {
            presenter.onClickImport();
        }
        if (v.getId() == R.id.rel_export) {
            presenter.onClickExport();
        }
    }

}
