package com.example.ashenone.woke;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;



public class AlarmSetFragment extends Fragment {
    private TimePicker tClock;
    private TextView tView;
    private Button set;
    private CheckBox sun;
    private CheckBox mon;
    private CheckBox tues;
    private CheckBox wed;
    private CheckBox thurs;
    private CheckBox fri;
    private CheckBox sat;
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set, container, false);
    }

    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){

        tClock = v.findViewById(R.id.timePicker1);
        tView = v.findViewById(R.id.Ans);
        set=v.findViewById(R.id.set);
        sun = v.findViewById(R.id.sun);
        mon = v.findViewById(R.id.mon);
        tues = v.findViewById(R.id.tues);
        wed = v.findViewById(R.id.wed);
        thurs = v.findViewById(R.id.thurs);
        fri = v.findViewById(R.id.fri);
        sat = v.findViewById(R.id.sat);

        tClock.setCurrentHour(8);
        tClock.setCurrentMinute(45);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = tClock.getCurrentHour();
                int min = tClock.getCurrentMinute();
                String time;
                if(hour>12){
                    hour-=12;
                    time= Integer.toString(hour)+":"+Integer.toString(min)+" PM";
                }else{
                    time= Integer.toString(hour)+":"+Integer.toString(min)+" AM";
                }
                String days="";
                if(sun.isChecked())
                    days=days+"Su";
                if(mon.isChecked())
                    days=days+"M";
                if(tues.isChecked())
                    days=days+"T";
                if(wed.isChecked())
                    days=days+"W";
                if(thurs.isChecked())
                    days=days+"Th";
                if(fri.isChecked())
                    days=days+"F";
                if(sat.isChecked())
                    days=days+"Sa";
                if(!days.equals(""))
                    time=time+" "+days;
                tView.setText(time);
            }
        });
    }
}
