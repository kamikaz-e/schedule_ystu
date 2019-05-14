package com.example.misha.myapplication.module.editData.page;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputFilter;
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
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.ScheduleApp;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.database.AbsDao;
import com.example.misha.myapplication.entity.EditDataModel;
import com.example.misha.myapplication.entity.SimpleItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.misha.myapplication.Constants.FRAGMENT_AUDIENCES;
import static com.example.misha.myapplication.Constants.FRAGMENT_EDIT_DATA;
import static com.example.misha.myapplication.Constants.FRAGMENT_EDUCATORS;
import static com.example.misha.myapplication.Constants.FRAGMENT_SUBJECTS;
import static com.example.misha.myapplication.Constants.FRAGMENT_TYPELESSONS;

public class EditDataFragmentPage extends BaseMainFragment implements EditDataFragmentPageView, TextView.OnEditorActionListener {



    private EditText inputItem;
    private ListView listViewItems;
    private EditDataPagePresenter presenter;


    public static EditDataFragmentPage newInstance(EditDataModel editDataModel) {
        Bundle args = new Bundle();
        args.putParcelable(FRAGMENT_EDIT_DATA, editDataModel);
        EditDataFragmentPage fragment = new EditDataFragmentPage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditDataModel editDataModel = getArguments().getParcelable(FRAGMENT_EDIT_DATA);
        presenter = new EditDataPagePresenter(editDataModel);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.init();
    }

    @Override
    public View onCreateView(@NotNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_edit_data, container, false);
        inputItem = view.findViewById(R.id.input_subject);
        listViewItems = view.findViewById(R.id.list_subjects);
        listViewItems.setOnItemClickListener((parent, itemClicked, position, id) -> presenter.onClearClick(position));
        inputItem.setOnEditorActionListener(this);
        if (currentFragment == FRAGMENT_SUBJECTS) {
            inputItem.setHint("Предмет");
            inputItem.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});
        }
        if (currentFragment == FRAGMENT_AUDIENCES) {
            inputItem.setHint("Аудитория");
            inputItem.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
        }
        if (currentFragment == FRAGMENT_EDUCATORS) {
            inputItem.setHint("Преподаватель");
            inputItem.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(60)});
        }
        if (currentFragment == FRAGMENT_TYPELESSONS) {
            inputItem.setHint("Тип занятия");
            inputItem.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        }
        return view;
    }

    public Dialog onCreateDialogDeleteItem(int position, AbsDao absDao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false)
                .setPositiveButton("Подтвердить", (dialog, id) -> presenter.deleteItem(position))
                .setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle(ScheduleApp.getStr(R.string.delete_object, presenter.getNameAt(position)));
        return builder.create();
    }

    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }

    public void updateView(ArrayList<SimpleItem> listItems) {
        getActivity().getResources().getString()
        ArrayAdapter<SimpleItem> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listViewItems.setAdapter(adapter);
    }

    @Override
    public void setupWidgets(EditDataModel editDataModel) {
        inputItem.setHint(editDataModel.getHint());
        inputItem.setInputType(editDataModel.getInputType());
        inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editDataModel.getMaxLenth())});
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((actionId == EditorInfo.IME_ACTION_DONE) ||
                ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
            String itemName = inputItem.getText().toString();
            itemName = itemName.trim().replaceAll(" +", " ");
            if (TextUtils.isEmpty(itemName) || itemName.equals(" ")) {
                if (currentFragment.equals(FRAGMENT_SUBJECTS)) {inputItem.setError("Введите предмет");}
                if (currentFragment.equals(FRAGMENT_AUDIENCES)) {inputItem.setError("Введите аудиторию");}
                if (currentFragment.equals(FRAGMENT_EDUCATORS)) {inputItem.setError("Введите преподавателя");}
                if (currentFragment.equals(FRAGMENT_TYPELESSONS)) {inputItem.setError("Введите тип предмета");}
                return true;
            }
            presenter.insert(itemName);
            inputItem.getText().clear();
            return true;
        } else {
            return false;
        }
    }
}
