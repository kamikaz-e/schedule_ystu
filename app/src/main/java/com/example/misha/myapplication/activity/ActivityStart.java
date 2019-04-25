package com.example.misha.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.database.dao.AudienceDao;
import com.example.misha.myapplication.database.dao.CallDao;
import com.example.misha.myapplication.database.dao.EducatorDao;
import com.example.misha.myapplication.database.dao.LessonDao;
import com.example.misha.myapplication.database.dao.SubjectDao;
import com.example.misha.myapplication.database.entity.Audience;
import com.example.misha.myapplication.database.entity.Calls;
import com.example.misha.myapplication.database.entity.Educator;
import com.example.misha.myapplication.database.entity.Lesson;
import com.example.misha.myapplication.database.entity.Subject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.misha.myapplication.Constants.audiences_import;
import static com.example.misha.myapplication.Constants.call_schedule;
import static com.example.misha.myapplication.Constants.educators_import;
import static com.example.misha.myapplication.Constants.schedule_import;
import static com.example.misha.myapplication.Constants.subjects_import;


public class ActivityStart extends AppCompatActivity {


    final Context context = this;
    private ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);


        Button start_buttonOne = findViewById(R.id.start_buttonOne);
        start_buttonOne.setOnClickListener(v -> onCreateDialogImport().show());
        Button start_buttonTwo = findViewById(R.id.startbuttonTwo);
        start_buttonTwo.setOnClickListener(v -> {

        });

    }

    public Dialog onCreateDialogImport() {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        builder.setCancelable(false).setPositiveButton("Импортировать", (dialog, id) -> {
            Toast.makeText(context, "Позже здесь будет импорт расписания", Toast.LENGTH_SHORT).show();
        }).setNegativeButton("Отмена", (dialog, id) -> {
        });
        return builder.create();
    }



}
