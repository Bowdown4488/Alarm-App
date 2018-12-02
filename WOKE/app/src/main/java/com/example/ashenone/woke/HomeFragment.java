package com.example.ashenone.woke;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    private RecyclerView homeRecycler;
    private HomeAlarmAdapter adapter;
    private RecyclerView.LayoutManager manager;

    private FloatingActionButton addBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        homeRecycler = view.findViewById(R.id.homeRecycler);

        manager = new LinearLayoutManager(view.getContext());
        homeRecycler.setLayoutManager(manager);

        adapter = new HomeAlarmAdapter(HomeFragment.this);
        homeRecycler.setAdapter(adapter);

        addBtn = view.findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Bundle savedArgs = getArguments();                  //loads data passed from main activity
        if(savedArgs!=null){
            adapter.addItem(savedArgs.getString("TIME"), savedArgs.getString("DAY"));

        }

        Log.d("--- a10_fragments","HomeFragment onViewCreated");

    }
}
