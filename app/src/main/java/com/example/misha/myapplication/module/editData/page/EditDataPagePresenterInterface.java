package com.example.misha.myapplication.module.editData.page;

public interface EditDataPagePresenterInterface {

    void onClearClick(int position);

    void insert(String itemName, int type);

    void deleteItem(int position);

    String getNameAt(int position);
}
