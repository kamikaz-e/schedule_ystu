package com.example.misha.myapplication.module.editData.page;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputFilter;
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

import static com.example.misha.myapplication.Constants.FRAGMENT_EDIT_DATA;

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
        EditDataModel editDataModel = getArguments().getParcelable(FRAGMENT_EDIT_DATA);
        inputItem = view.findViewById(R.id.input_subject);
        listViewItems = view.findViewById(R.id.list_subjects);
        listViewItems.setOnItemClickListener((parent, itemClicked, position, id) -> presenter.onClearClick(position));
        inputItem.setOnEditorActionListener(this);
        inputItem.setHint(editDataModel.getHint());
        inputItem.setInputType(editDataModel.getInputType());
        inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editDataModel.getMaxLenth())});
        return view;
    }

    public Dialog onCreateDialogDeleteItem(int position, AbsDao absDao) {
        EditDataModel editDataModel = getArguments().getParcelable(FRAGMENT_EDIT_DATA);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false)
                .setPositiveButton("Подтвердить", (dialog, id) -> presenter.deleteItem(position))
                .setNegativeButton("Отмена", (dialog, id) -> dialog.cancel()).setTitle(ScheduleApp.getStr(editDataModel.getTitle(), presenter.getNameAt(position)));
        return builder.create();
    }

    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }

    public void updateView(ArrayList<SimpleItem> listItems) {
        //  getActivity().getResources().getString();
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
        EditDataModel editDataModel = getArguments().getParcelable(FRAGMENT_EDIT_DATA);
        if ((actionId == EditorInfo.IME_ACTION_DONE) ||
                ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
            String itemName = inputItem.getText().toString();
            itemName = itemName.trim().replaceAll(" +", " ");
            if (TextUtils.isEmpty(itemName) || itemName.equals(" ")) {
                inputItem.setError(String.valueOf(editDataModel.getError()));
                return true;
            }
            presenter.insert(itemName, editDataModel.getType());
            inputItem.getText().clear();
            return true;
        } else {
            return false;
        }
    }
}
