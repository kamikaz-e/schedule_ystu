package com.example.misha.myapplication.module.calls;

import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateUtils;

import com.example.misha.myapplication.common.core.BaseActivity;
import com.example.misha.myapplication.common.core.BaseMainPresenter;
import com.example.misha.myapplication.data.database.dao.CallDao;
import com.example.misha.myapplication.data.preferences.Preferences;
import com.example.misha.myapplication.entity.Calls;

import java.util.ArrayList;
import java.util.Calendar;

public class CallsPresenter extends BaseMainPresenter<CallsFragmentView> implements CallsPresenterInterface {

    private Context context;

    public CallsPresenter(BaseActivity context) {
        this.context = context;
    }

    @Override
    public void init() {
    }

    private Calendar calendarTimeCalls = Calendar.getInstance();

    @Override
    public void onClick(int position) {
        Preferences.getInstance().setSelectedPositionLesson(position);
        Preferences.getInstance().setCallsOpened(true);

        new TimePickerDialog(context, timeOne,
                calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                calendarTimeCalls.get(Calendar.MINUTE), true)
                .show();
    }

    private TimePickerDialog.OnTimeSetListener timeOne = (view, hourOfDay, minute) -> {
        calendarTimeCalls.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendarTimeCalls.set(Calendar.MINUTE, minute);
        setTime();
    };


    private void setTime() {
        if (Preferences.getInstance().isCallsOpened()) {
            Preferences.getInstance().setSelectDate(DateUtils.formatDateTime(context,
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME) + " - ");
        } else {
            String selectFullTime = Preferences.getInstance().getSelectDate() + DateUtils.formatDateTime(context,
                    calendarTimeCalls.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
            ArrayList<Calls> callsList = CallDao.getInstance().getAllData();
            callsList.get(Preferences.getInstance().getSelectedPositionLesson()).setName(selectFullTime);

            getView().updateView(callsList);
            CallDao.getInstance().updateItemByID(callsList.get(Preferences.getInstance().getSelectedPositionLesson()));

        }
        if (Preferences.getInstance().isCallsOpened()) {
            new TimePickerDialog(context, timeOne,
                    calendarTimeCalls.get(Calendar.HOUR_OF_DAY),
                    calendarTimeCalls.get(Calendar.MINUTE), true)
                    .show();
            Preferences.getInstance().setCallsOpened(false);
        }
    }
}
