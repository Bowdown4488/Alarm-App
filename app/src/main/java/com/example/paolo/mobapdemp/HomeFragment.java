package com.example.paolo.mobapdemp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private RecyclerView homeRecycler;
    private HomeAlarmAdapter adapter;
    private RecyclerView.LayoutManager manager;

    private FloatingActionButton addBtn;

    public static final String ALARM_UPDATE_TAG =
            "com.example.paolo.mobapdemp.ActivityReceiver";

    private BroadcastReceiver receiver = new ActivityReceiver();

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

        DividerItemDecoration dividerItemDecoration  = new DividerItemDecoration(homeRecycler.getContext(),((LinearLayoutManager) manager).VERTICAL);
        homeRecycler.setHasFixedSize(true);
        homeRecycler.setLayoutManager(manager);
        homeRecycler.addItemDecoration(dividerItemDecoration);

        adapter = new HomeAlarmAdapter(HomeFragment.this);
        homeRecycler.setAdapter(adapter);

        addBtn = view.findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmSetFragment fragment = new AlarmSetFragment();
                swapFragment(fragment);
            }
        });


        Bundle savedArgs = getArguments();                  //loads data passed from main activity
        if(savedArgs!=null){
            adapter.addItem(savedArgs.getInt("HOUR"), savedArgs.getInt("MINUTE"), savedArgs.getInt("DAY"));             //adds rows of alarms

        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(ALARM_UPDATE_TAG);
        getActivity().registerReceiver(receiver, filter);
        Log.d("--- a10_fragments","HomeFragment onViewCreated");

    }

    private void swapFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public class ActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("ALARMERINO", "onReceive: BROADCAST RECEIVED");
            Toast.makeText(context,"hajimemashite",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
