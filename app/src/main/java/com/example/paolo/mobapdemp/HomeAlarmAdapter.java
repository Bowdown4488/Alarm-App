package com.example.paolo.mobapdemp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeAlarmAdapter extends RecyclerView.Adapter<HomeAlarmHolder> {


    private ArrayList<HomeAlarmModel> homeAlarmList;
    private Fragment parentFragment;
    private int counter = 0;
    private HomeAlarmHolder currentHolder;
    private int size;
    private ArrayList<String> referenceList;

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
        holder.addAlarmSwitchListener(new AlarmSwitchListener());
        holder.addItemViewListener(new ItemViewListener());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAlarmHolder homeAlarmHolder, int i) {
        homeAlarmHolder.setTimeTxt(Integer.toString(homeAlarmList.get(i).getAlarmHour()) + ":" + Integer.toString(homeAlarmList.get(i).getAlarmMinute()));                //how the data gets loaded into the rows
        this.counter = i;
        this.currentHolder = homeAlarmHolder;
    }

    @Override
    public int getItemCount() {
        return homeAlarmList.size();
    }

    public void addItem(int hour, int minute, int day){
        homeAlarmList.add(new HomeAlarmModel(hour, minute, day));
        notifyItemInserted(homeAlarmList.size()-1);
    }

    public class AlarmSwitchListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent alarmIntent = new Intent(HomeFragment.ALARM_UPDATE_TAG);
            alarmIntent.putExtra("Size", size);
            for(int i = 0;i<size;i++){
                alarmIntent.putExtra("LIST"+i, HomeAlarmAdapter.this.referenceList.get(i));
//                Log.d("tester",HomeAlarmAdapter.this.referenceList.get(i));
            }

            PendingIntent pendingIntent = PendingIntent.getBroadcast(HomeAlarmAdapter.this.parentFragment.getActivity(),
                    0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager)HomeAlarmAdapter.this.parentFragment.getActivity().getSystemService(Context.ALARM_SERVICE);

            if(HomeAlarmAdapter.this.currentHolder.getAlarmSwitch().isChecked() == false){
                manager.cancel(pendingIntent);
                Log.d("ALARMERINO", "onClick: ALARM CANCELED");

            }else{
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, homeAlarmList.get(counter).getAlarmHour());
                calendar.set(Calendar.MINUTE, homeAlarmList.get(counter).getAlarmMinute());

                Log.d("ALARMERINO", Integer.toString(Calendar.HOUR_OF_DAY, homeAlarmList.get(counter).getAlarmHour()));
                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                Log.d("ALARMERINO", "onClick: ALARM SET");

            }
        }
    }

    public class ItemViewListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            AlarmSetFragment fragment = new AlarmSetFragment();
            Bundle homeBundle = new Bundle();
            homeBundle.putInt("HOUR", homeAlarmList.get(counter).getAlarmHour());
            homeBundle.putInt("MINUTE", homeAlarmList.get(counter).getAlarmMinute());                            //dummy data for time
            homeBundle.putInt("DAY", 5);
            homeBundle.putInt("Size", HomeAlarmAdapter.this.size);
            homeBundle.putStringArrayList("LIST", HomeAlarmAdapter.this.referenceList);

            fragment.setArguments(homeBundle);
            swapFragment(fragment, HomeAlarmAdapter.this.parentFragment);
        }
    }

    private void swapFragment(Fragment fragment, Fragment parentFragment){
        FragmentTransaction transaction = parentFragment.getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setReferenceList(ArrayList<String> list){
        this.referenceList = list;
    }
}
