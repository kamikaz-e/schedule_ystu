package com.example.misha.myapplication.module.editData.page;

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
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.ScheduleApp;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.entity.EditDataModel;
import com.example.misha.myapplication.entity.SimpleItem;
import com.example.misha.myapplication.module.editData.page.edit.DialogFragmentEditData;
import com.example.misha.myapplication.module.schedule.edit.page.dialog.DialogFragmentListItems;
import com.example.misha.myapplication.module.schedule.edit.page.dialog.DialogFragmentListItemsAdapter;
import com.example.misha.myapplication.module.schedule.edit.page.dialog.addData.DialogFragmentAddData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.misha.myapplication.Constants.FRAGMENT_AUDIENCES;
import static com.example.misha.myapplication.Constants.FRAGMENT_EDIT_DATA;
import static com.example.misha.myapplication.Constants.FRAGMENT_EDUCATORS;
import static com.example.misha.myapplication.Constants.FRAGMENT_SUBJECTS;
import static com.example.misha.myapplication.Constants.FRAGMENT_TYPELESSONS;
import static com.example.misha.myapplication.Constants.ITEMS;

public class EditDataFragmentPage extends BaseMainFragment implements EditDataFragmentPageView, TextView.OnEditorActionListener {

    private EditText inputItem;
    private EditDataPagePresenter presenter;
    private RecyclerView rvItems;
    private EditDataFragmentPageAdapter dialogFragmentListItemsAdapter;

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
        rvItems = view.findViewById(R.id.rv_dialog_edit_data);
        rvItems.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        inputItem = view.findViewById(R.id.input_item);
        inputItem.setOnEditorActionListener(this);
        inputItem.setHint(editDataModel.getHint());
        inputItem.setInputType(editDataModel.getInputType());
        inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editDataModel.getMaxLenth())});
        return view;
    }

 /*   @Override
    public void showAddDataDialog(ArrayList<? extends SimpleItem> items, int fragmentCode) {
        DialogFragmentEditData dialogFragment = null;
        if (fragmentCode == FRAGMENT_SUBJECTS) {
            dialogFragment = DialogFragmentEditData.newInstance(new EditDataModel(R.string.error_subject, R.string.hint_subject, R.string.title_subject, InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, 60, FRAGMENT_SUBJECTS));
        }
        if (fragmentCode == FRAGMENT_AUDIENCES) {
            dialogFragment = DialogFragmentEditData.newInstance(new EditDataModel(R.string.error_audience, R.string.hint_audience, R.string.title_audience, InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, 14, FRAGMENT_AUDIENCES));
        }
        if (fragmentCode == FRAGMENT_EDUCATORS) {
            dialogFragment = DialogFragmentEditData.newInstance(new EditDataModel(R.string.error_educator, R.string.hint_educator, R.string.title_educator, InputType.TYPE_TEXT_FLAG_CAP_WORDS, 60, FRAGMENT_EDUCATORS));
        }
        if (fragmentCode == FRAGMENT_TYPELESSONS) {
            dialogFragment = DialogFragmentEditData.newInstance(new EditDataModel(R.string.error_typelesson, R.string.hint_typelesson, R.string.title_typelesson, InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, 20, FRAGMENT_TYPELESSONS));
        }
        dialogFragment.show(getChildFragmentManager(), DialogFragmentEditData.class.getSimpleName());
    }
*/
   /* public Dialog onCreateDialogMenuItem(int position) {
        EditDataModel editDataModel = getArguments().getParcelable(FRAGMENT_EDIT_DATA);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false)
                .setPositiveButton(R.string.ack, (dialog, id) -> presenter.deleteItem(position))
                .setNegativeButton(R.string.cancel, (dialog, id) -> dialog.cancel()).setTitle(ScheduleApp.getStr(editDataModel.getTitle(), presenter.getNameAt(position)));
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }*/

    @Override
    public void showEditDataDialog(ArrayList<? extends SimpleItem> items, int position) {
        DialogFragmentEditData dialogFragment;
            dialogFragment = DialogFragmentEditData.newInstance(new EditDataModel(R.string.error_subject, R.string.hint_subject, R.string.title_editSubject, InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, 60, FRAGMENT_SUBJECTS, position, presenter.getNameAt(position)));
        dialogFragment.show(getChildFragmentManager(), DialogFragmentEditData.class.getSimpleName());
    }

    @NotNull
    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }

    public void updateItemsAdapter(ArrayList<SimpleItem> listItems) {
        EditDataFragmentPageAdapter editDataFragmentPageAdapter = new EditDataFragmentPageAdapter(listItems, (position, view1) -> presenter.onClearClick(position));
        rvItems.setAdapter(editDataFragmentPageAdapter);
    }

    @Override
    public void setupWidgets(EditDataModel editDataModel) {
        inputItem.setHint(editDataModel.getHint());
        inputItem.setInputType(editDataModel.getInputType());
        inputItem.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editDataModel.getMaxLenth())});
    }
    @Override
    public void onActivityResult(int requestCode, int resultOk, Intent data) {
        ArrayList<SimpleItem> itemList = presenter.getItemList();
        updateItemsAdapter(itemList);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        EditDataModel editDataModel = getArguments().getParcelable(FRAGMENT_EDIT_DATA);
        if ((actionId == EditorInfo.IME_ACTION_DONE) ||
                ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
            String itemName = inputItem.getText().toString();
            itemName = itemName.trim().replaceAll(" +", " ");
            if (TextUtils.isEmpty(itemName) || itemName.equals(" ")) {
                inputItem.setError(getText(editDataModel.getError()));
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
