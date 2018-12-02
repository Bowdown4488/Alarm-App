package com.example.ashenone.woke;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeAlarmAdapter extends RecyclerView.Adapter<HomeAlarmHolder> {


    private ArrayList<HomeAlarmModel> homeAlarmList;
    private Fragment parentFragment;

    public HomeAlarmAdapter(Fragment fragment){
        homeAlarmList = new ArrayList<>();
        this.parentFragment = fragment;

    }
    @NonNull
    @Override
    public HomeAlarmHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.home_recycler_row, viewGroup, false);


        HomeAlarmHolder holder = new HomeAlarmHolder(view, this.parentFragment);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAlarmHolder homeAlarmHolder, int i) {
        homeAlarmHolder.setTimeTxt(homeAlarmList.get(i).getAlarmTime());                //how the data gets loaded into the rows
    }

    @Override
    public int getItemCount() {
        return homeAlarmList.size();
    }

    public void addItem(String time,String day){
        homeAlarmList.add(new HomeAlarmModel(time, day));
        notifyItemInserted(homeAlarmList.size()-1);
    }
}
