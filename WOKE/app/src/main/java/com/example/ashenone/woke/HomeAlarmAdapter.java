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
        homeAlarmList = new ArrayList<HomeAlarmModel>();
        this.parentFragment = fragment;
        Date currentTime = Calendar.getInstance().getTime();                        //gets current time

        homeAlarmList.add(new HomeAlarmModel(currentTime.toString(), "hi"));                //test values
        homeAlarmList.add(new HomeAlarmModel(currentTime.toString(), "hi"));
        homeAlarmList.add(new HomeAlarmModel(currentTime.toString(), "hi"));
        homeAlarmList.add(new HomeAlarmModel(currentTime.toString(), "hi"));

    }
    @NonNull
    @Override
    public HomeAlarmHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.home_recycler_row, viewGroup, false);

        //(6) The view created must be given to a holder. The holder will serve as the in-between
        //    system that interacts with the view.
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
