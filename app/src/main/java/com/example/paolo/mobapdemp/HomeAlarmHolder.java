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
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class HomeAlarmHolder extends RecyclerView.ViewHolder {

    private MainActivity activity;
    private TextView timeTxt;
    private Switch alarmSwitch;
    private Fragment parentFragment;

    public HomeAlarmHolder(@NonNull final View itemView, final Fragment parentFragment) {
        super(itemView);

        timeTxt = itemView.findViewById(R.id.timeTxt);
        alarmSwitch = itemView.findViewById(R.id.alarmSwitch);
        this.parentFragment = parentFragment;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void setTimeTxt(String text){
        timeTxt.setText(text);
    }

    public void setAlarmSwitch(boolean active){
        alarmSwitch.setChecked(active);
    }

    public void startAlarm(){
        Intent alarmIntent = new Intent(HomeFragment.ALARM_UPDATE_TAG);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(HomeAlarmHolder.this.parentFragment.getActivity(),
                0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager)HomeAlarmHolder.this.parentFragment.getActivity().getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000, pendingIntent);
        Log.d("ALARMERINO", "onClick: ALARM SET");
    }

    public void addAlarmSwitchListener(View.OnClickListener listener){
        alarmSwitch.setOnClickListener(listener);
    }

    public void addItemViewListener(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
    }
}
