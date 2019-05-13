package com.example.misha.myapplication.module.calls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misha.myapplication.R;
import com.example.misha.myapplication.common.core.BaseMainFragment;
import com.example.misha.myapplication.common.core.BasePresenter;
import com.example.misha.myapplication.data.database.entity.Calls;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class CallsFragment extends BaseMainFragment implements CallsFragmentView {


    private CallsFragmentAdapter callsAdapter;
    private CallsPresenter presenter;

    @Override
    public void onResume() {
        super.onResume();
        getContext().setCurrentTitle("Расписание звонков");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CallsPresenter(getContext());
        callsAdapter = new CallsFragmentAdapter(presenter);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call_schedule, container, false);
        RecyclerView rvCalls = view.findViewById(R.id.rv_calls);
        rvCalls.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCalls.setAdapter(callsAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    protected BasePresenter getSchedulePagePresenter() {
        return presenter;
    }


    public void updateView(ArrayList<Calls> callsList) {
        callsAdapter.setCallsList(callsList);
        callsAdapter.notifyDataSetChanged();
    }


}
