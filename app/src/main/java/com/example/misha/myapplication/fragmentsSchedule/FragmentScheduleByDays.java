package com.example.misha.myapplication.fragmentsSchedule;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.misha.myapplication.Constants;
import com.example.misha.myapplication.Preferences;
import com.example.misha.myapplication.R;
import com.example.misha.myapplication.activity.BaseFragment;
import com.example.misha.myapplication.adapter.tabDays.schedule.TabDaysAdapter;
import com.example.misha.myapplication.adapter.tabDays.schedule.TabDaysPagerAdapter;

import java.util.Calendar;
import java.util.zip.Inflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;

import static com.example.misha.myapplication.activity.MainActivity.WEEK_CODE;

public class FragmentScheduleByDays extends BaseFragment {

    TabDaysPagerAdapter pagerAdapter;
    TabDaysAdapter adapterTabDays;
    RecyclerView dayTabs;
    private ViewPager viewPager;
    private int selectedWeek;
    Spinner spinner;
    long differentBetweenDate = 0;
    long selectDate = 0;
    long currWeek = 0;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterTabDays = new TabDaysAdapter((position, view) -> {
            viewPager.setCurrentItem(position);

        });

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                adapterTabDays.setSelection(position);
                Preferences.getInstance().setSelectedPositionTabDays(position);
            }
        });
        pagerAdapter = new TabDaysPagerAdapter(getChildFragmentManager());

        spinner = getActivity().findViewById(R.id.spinner);
        spinner.setVisibility(View.VISIBLE);
        setHasOptionsMenu(true);
       toolbar =  getActivity().findViewById(R.id.toolbar);

       calculateDate();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(6);
        dayTabs = view.findViewById(R.id.rvTab);
        dayTabs.setAdapter(adapterTabDays);
        currentDay();

        return view;
    }

    private void currentDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                viewPager.setCurrentItem(0);
                adapterTabDays.setSelection(0);
                break;
            case 2:
                viewPager.setCurrentItem(0);
                adapterTabDays.setSelection(0);
                break;
            case 3:
                viewPager.setCurrentItem(1);
                adapterTabDays.setSelection(1);
                break;
            case 4:
                viewPager.setCurrentItem(2);
                adapterTabDays.setSelection(2);
                break;
            case 5:
                viewPager.setCurrentItem(3);
                adapterTabDays.setSelection(3);
                break;
            case 6:
                viewPager.setCurrentItem(4);
                adapterTabDays.setSelection(4);
                break;
            case 7:
                viewPager.setCurrentItem(5);
                adapterTabDays.setSelection(5);
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_empty, menu);
        menu.findItem(R.id.button).setIcon(R.drawable.ic_editor);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.button) {
            FragmentEditSchedule fragment = new FragmentEditSchedule();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentFrame, fragment)
                    .commit();
            Intent intent = new Intent();
            intent.putExtra(Constants.SELECTED_WEEK, (int) currWeek);
            sendResultToTarget(FragmentEditSchedule.class, WEEK_CODE, FragmentActivity.RESULT_OK, intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WEEK_CODE) {
            selectedWeek = data.getIntExtra(Constants.SELECTED_WEEK, 0);
            pagerAdapter.setWeek(selectedWeek);
            adapterTabDays = new TabDaysAdapter((position, view) ->
                    viewPager.setCurrentItem(position));
            adapterTabDays.setSelection(viewPager.getCurrentItem());
            dayTabs.setAdapter(adapterTabDays);

        }
    }
    private void calculateDate() {
        Calendar calendar = Calendar.getInstance();
        selectDate = Preferences.getInstance().getSemestStart();
        differentBetweenDate = calendar.getTimeInMillis() - selectDate;
        currWeek = (differentBetweenDate / (7 * 24 * 60 * 60 * 1000));
    }

}
