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

    private TextView timeTxt;
    private Switch alarmSwitch;
    private Fragment parentFragment;

    public HomeAlarmHolder(@NonNull final View itemView, final Fragment parentFragment) {
        super(itemView);

        timeTxt = itemView.findViewById(R.id.timeTxt);
        alarmSwitch = itemView.findViewById(R.id.alarmSwitch);
        this.parentFragment = parentFragment;

    }

    public void setTimeTxt(String text){
        timeTxt.setText(text);
    }

    public void setAlarmSwitch(boolean active){
        alarmSwitch.setChecked(active);
    }

    public void addAlarmSwitchListener(View.OnClickListener listener){
        alarmSwitch.setOnClickListener(listener);
    }

    public void addItemViewListener(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
    }

    public Switch getAlarmSwitch(){
        return this.alarmSwitch;
    }
}
