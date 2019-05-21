package com.example.misha.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.misha.myapplication.R;


public class ActivityStart extends AppCompatActivity {

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
        LayoutInflater li = LayoutInflater.from(this);
        View view = li.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setCancelable(false).setPositiveButton("Загрузить расписание", (dialog, id) -> {
            Toast.makeText(this, "Позже здесь будет загрузка расписания", Toast.LENGTH_SHORT).show();
        }).setNegativeButton("Отмена", (dialog, id) -> {
        });
        return builder.create();
    }

}
