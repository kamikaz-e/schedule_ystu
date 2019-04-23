package com.example.misha.myapplication.startFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.entity.Audience;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground;
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal;


public class FragmentStartAudience extends Fragment {


    EditText input_audience;
    ListView list_audiences;

    ArrayList<Audience> audience_list = new ArrayList<>();
    public ArrayAdapter<String> adapter;
    Button clear_audiences;
    Button next;
    int select_item;

    public FragmentStartAudience() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_audiences, container, false);
        Toolbar profile_toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(profile_toolbar);


        clear_audiences = view.findViewById(R.id.clearAudiences);
        next = view.findViewById(R.id.next);
        input_audience = view.findViewById(R.id.inputAudience);
        list_audiences = view.findViewById(R.id.listAudiences);


        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, audience_list);
        list_audiences.setAdapter(adapter);

        list_audiences.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                onCreateDialogDeleteItem(position).show();
            }
        });

        clear_audiences.setBackgroundResource(R.drawable.ic_clear);
        next.setBackgroundResource(R.drawable.ic_start_settings_ok);
        next.setOnClickListener(v -> {
            FragmentStartEducator fragment= new FragmentStartEducator();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        clear_audiences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialogClear().show();
            }
        });
        updateList();

            new MaterialTapTargetPrompt.Builder(getActivity())
                    .setTarget(input_audience)
                    .setPromptBackground(new RectanglePromptBackground())
                    .setPromptFocal(new RectanglePromptFocal())
                    .setPrimaryText("Добавление аудитории")
                    .setSecondaryText("Аналогично, как и в предыдущем действии")
                    .setBackButtonDismissEnabled(true).setFocalColour(Color.rgb(200, 200, 255))
                    .setBackgroundColour(Color.rgb(100, 100, 255))
                    .setPrimaryTextColour(Color.rgb(255, 255, 255))
                    .setSecondaryTextColour(Color.rgb(255, 255, 255))
                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                            if (state == MaterialTapTargetPrompt.STATE_FINISHED || state == MaterialTapTargetPrompt.STATE_DISMISSED) {

                            }
                        }
                    })
                    .show();

        input_audience.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))){
                    input_audience.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                    String audienceName = input_audience.getText().toString();
                    if(TextUtils.isEmpty(audienceName)) {
                        input_audience.setError("Введите аудиторию");
                        return true;
                    }
                    Audience audience = new Audience();
                    audience.setName(audienceName);
                    AudienceDao.getInstance().insertItem(audience);


                    input_audience.setText("");
                    updateList();
                    adapter.notifyDataSetChanged();
                    return true;
                }
                else{
                    return false;
                }
            }
        });
        return view;
    }

    public Dialog onCreateDialogDeleteItem(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
               Audience audience = audience_list.get(position);
               AudienceDao.getInstance().deleteItemById(Long.parseLong(audience.getId()));
                updateList();
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Удалить аудиторию «"+select_item+"»?");
        return builder.create();
    }

    public Dialog onCreateDialogClear() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setCancelable(false).setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                clear_audiences();
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setTitle("Очистить аудитории?");
        return builder.create();
    }

    void clear_audiences(){
       AudienceDao.getInstance().deleteAll();
        adapter.notifyDataSetChanged();
        updateList();
    }


    public void updateList(){

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, audience_list);
        list_audiences.setAdapter(adapter);
        audience_list= AudienceDao.getInstance().getAllData();

    }

}
