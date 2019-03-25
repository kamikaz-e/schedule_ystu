package com.example.misha.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.misha.myapplication.data.ScheduleClass;
import com.example.misha.myapplication.data.ScheduleClass.subjects;
import com.example.misha.myapplication.data.ScheduleDB;
import java.util.ArrayList;

public class activitysubject extends AppCompatActivity
        implements fragment_start_subjects.OnFragmentInteractionListener,fragment_start_audiences.OnFragmentInteractionListener,fragment_start_educators.OnFragmentInteractionListener{
  EditText input_subject;
  ListView list_subjects;
  private ScheduleDB ScheduleDB;
  final Context context = this;

  final ArrayList<String> subject_list = new ArrayList<>();
  public ArrayAdapter<String> adapter;
  Button button_toolbar;
  String select_item="";

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activitysubject);

    android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Fragment fragment = new fragment_start_subjects();
    if (fragment != null) {
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.content_frame, fragment);
      ft.commit();

    }
  }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
  }

