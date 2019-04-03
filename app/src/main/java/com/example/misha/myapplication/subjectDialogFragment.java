package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleDB;
import com.example.misha.myapplication.model.Subject;

import java.util.ArrayList;


//Todo прочитать про наследование инкапсуляцию интерфейсы абстрактные классы и generic.
public class subjectDialogFragment extends DialogFragment {

    private ScheduleDB ScheduleDB;

    public static final String SUBJECTS = "SUBJECTS";
    public static final String POSITION = "POSITION";

    private int clickedPosition;

    private ArrayList<Subject> subjects;

    public static subjectDialogFragment newInstance(int position, ArrayList<Subject> subjects) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(SUBJECTS, subjects);
        args.putInt(POSITION, position);
        subjectDialogFragment fragment = new subjectDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }



    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ScheduleDB = new ScheduleDB();
        clickedPosition = getArguments().getInt(POSITION);
        subjects = getArguments().getParcelableArrayList(SUBJECTS);
        LayoutInflater li = LayoutInflater.from(getActivity());
        View view = li.inflate(R.layout.typelesson_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        final EditText editTextOne =  view.findViewById(R.id.typelesson_one);
        final EditText editTextTwo=  view.findViewById(R.id.typelesson_two);
        final EditText editTextThree =  view.findViewById(R.id.typelesson_three);

        ArrayList<String> array_typelesson = new ArrayList<>();
        SQLiteDatabase db = ScheduleDB.getReadableDatabase();
        String searchQuery = "select "+ ScheduleClass.typelessons.typelesson + " from " + ScheduleClass.typelessons.TABLE_NAME;
        Cursor cursor = db.rawQuery(searchQuery, null);
        while(cursor.moveToNext()) {
            array_typelesson.add(cursor.getString(0));
        }
       /* Dialog dialog = new Dialog(getBaseContext());
        dialog.setContentView();*/
        cursor.close();
        editTextOne.setText(array_typelesson.get(0));
        editTextTwo.setText(array_typelesson.get(1));
        editTextThree.setText(array_typelesson.get(2));
        builder.setCancelable(false).setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dismiss();
            }
        });
        return builder.create();
    }

}
