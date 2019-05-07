package com.example.misha.myapplication.module.editData.page.fragments;

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

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.database.dao.AudienceDao;
import com.example.misha.myapplication.data.database.entity.Audience;
import com.example.misha.myapplication.module.editData.page.EditDataPagePresenter;
import com.example.misha.myapplication.module.editData.page.EditDataFragmentPageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class FragmentPageAudience extends BaseMainFragment implements EditDataFragmentPageView {


    private EditText input_audience;
    private ListView list_audiences;

    private ArrayList<Audience> audience_list = new ArrayList<>();
    public ArrayAdapter<Audience> adapter;

    private EditDataPagePresenter presenter;

    public FragmentPageAudience() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EditDataPagePresenter();
    }



    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audience, container, false);
        input_audience = view.findViewById(R.id.input_audience);
        list_audiences = view.findViewById(R.id.list_audiences);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, audience_list);

        list_audiences.setAdapter(adapter);
        list_audiences.setOnItemClickListener((parent, view1, position, id) -> FragmentPageAudience.this.onCreateDialogDeleteItem(position).show());
        input_audience.setOnEditorActionListener((v, actionId, event) -> {
            if ((actionId == EditorInfo.IME_ACTION_DONE) ||
                    ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
                input_audience.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                String audienceName = input_audience.getText().toString();
                if (TextUtils.isEmpty(audienceName)) {
                    input_audience.setError("Введите аудиторию");
                    return true;
                }
                Audience audience = new Audience();
                audience.setName(audienceName);
                AudienceDao.getInstance().insertItem(audience);
                input_audience.getText().clear();
                presenter.onUpdateListView();
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
            Audience audience = audience_list.get(position);
            AudienceDao.getInstance().deleteItemById(Long.parseLong(audience.getId()));
            presenter.onUpdateListView();
        }).setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle("Удалить аудиторию «" + audience_list.get(position) + "»?");
        return builder.create();
    }

    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }

    public void updateListView() {
        audience_list = AudienceDao.getInstance().getAllData();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, audience_list);
        list_audiences.setAdapter(adapter);
    }
}
