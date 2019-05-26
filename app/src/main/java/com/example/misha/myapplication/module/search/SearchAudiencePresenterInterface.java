package com.example.misha.myapplication.module.search;

import android.view.View;

public interface SearchAudiencePresenterInterface {

    void onClickItem(String audience, View v);

    void loadAudiences(String date);

}
