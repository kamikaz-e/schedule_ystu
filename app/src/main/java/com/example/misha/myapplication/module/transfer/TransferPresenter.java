package com.example.misha.myapplication.module.transfer;

import android.app.Activity;
import android.os.Environment;
import android.widget.ShareActionProvider;

import androidx.fragment.app.FragmentActivity;

import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.entity.ExportData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;

public class TransferPresenter extends BaseMainPresenter<TransferFragmentView> implements TransferPresenterInterface {


    private Activity context;
    private ShareActionProvider shareActionProvider;

    public TransferPresenter(FragmentActivity context) {
        this.context = context;
    }

    @Override
    public void init() {
    }


    @Override
    public void onClickImport() {
    }

    @Override
    public void onClickExport() {
        export_data();
    }

    public void export_data() {
        String nameFile = "export.txt";
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonFromJavaArrayList = gsonBuilder.toJson(new ExportData());


        File file = new File(context.getExternalFilesDir(null), nameFile);
        FileOutputStream outputStream;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(jsonFromJavaArrayList.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}