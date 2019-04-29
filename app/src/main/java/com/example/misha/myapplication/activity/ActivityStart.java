package com.example.misha.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.misha.myapplication.R;


public class ActivityStart extends AppCompatActivity {


    final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);


        Button start_buttonOne = findViewById(R.id.start_button_import_schedule);
        start_buttonOne.setOnClickListener(v -> onCreateDialogImport().show());
        Button start_buttonTwo = findViewById(R.id.start_button_create_schedule);
        start_buttonTwo.setOnClickListener(v -> {

        });

    }

    public Dialog onCreateDialogImport() {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setView(view);
        builder.setCancelable(false).setPositiveButton("Загрузить расписание", (dialog, id) -> {
            Toast.makeText(context, "Позже здесь будет загрузка расписания", Toast.LENGTH_SHORT).show();
        }).setNegativeButton("Отмена", (dialog, id) -> {
        });
        return builder.create();
    }


}
