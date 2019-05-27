package com.example.misha.myapplication.module.transfer;

import android.app.Activity;
import android.os.Environment;

import androidx.fragment.app.FragmentActivity;

import com.example.misha.myapplication.Zipping;
import com.example.misha.myapplication.common.core.BaseMainPresenter;

import java.io.File;
import java.io.FileOutputStream;

public class TransferPresenter extends BaseMainPresenter<TransferFragmentView> implements TransferPresenterInterface {


    private Activity context;


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
        getView().onCreateDialogExport().show();
    }

    public void export_data(String nameFile) {
        //String textToWrite = "asdsddad";
        String fileObject = nameFile + ".txt";
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }


        File file = new File(context.getExternalFilesDir(null), fileObject);
        FileOutputStream outputStream;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] s = new String[1];
        s[0] = context.getExternalFilesDir(null) + "/" + fileObject;
        Zipping zipping = new Zipping();
        zipping.zip(s, context.getExternalFilesDir(null) + "/" + nameFile + ".zip");
        File file1 = new File(context.getExternalFilesDir(null) + "/" + fileObject);
        file1.delete();
    }


}