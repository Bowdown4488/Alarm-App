package com.example.paolo.mobapdemp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AlarmSetFragment extends Fragment {
    private TimePicker tClock;
    private TextView tView;
    private Button selectBtn;
    private Button set;
    private int hour;
    private int minute;
    private int size;
    private ArrayList<String> referenceList;

    private CheckBox sun;
    private CheckBox mon;
    private CheckBox tues;
    private CheckBox wed;
    private CheckBox thurs;
    private CheckBox fri;
    private CheckBox sat;

    List<Song> songList;

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
        selectBtn = v.findViewById(R.id.selectBtn);
        set = v.findViewById(R.id.set);
        sun = v.findViewById(R.id.sun);
        mon = v.findViewById(R.id.mon);
        tues = v.findViewById(R.id.tues);
        wed = v.findViewById(R.id.wed);
        thurs = v.findViewById(R.id.thurs);
        fri = v.findViewById(R.id.fri);
        sat = v.findViewById(R.id.sat);

        Bundle savedArgs = getArguments();                  //loads data passed from HomeFragment
        if(savedArgs!=null){
            this.hour = savedArgs.getInt("HOUR");
            this.minute = savedArgs.getInt("MINUTE");
            this.size = savedArgs.getInt("Size");
            this.referenceList = savedArgs.getStringArrayList("LIST");
        }

        tClock.setCurrentHour(hour);
        tClock.setCurrentMinute(minute);
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

                HomeFragment homeFragment = new HomeFragment();                       //put data to show here
                Bundle homeBundle = new Bundle();
                homeBundle.putInt("HOUR", hour);
                homeBundle.putInt("MINUTE", min);                            //dummy data for time
                homeBundle.putInt("DAY", 5);
                homeBundle.putInt("Size", size);
                homeFragment.setSongList(songList);

                homeFragment.setArguments(homeBundle);
                swapFragment(homeFragment);
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {                               //select songs listener
            @Override
            public void onClick(View v) {
                SetSongFragment setSongFragment = new SetSongFragment();                       //put data to show here

                Bundle setSongBundle = new Bundle();

                Log.d("songlist", "Pass list");
                int j = songList.size();
                Log.d("songlist",Integer.toString(j));

                for(int i = 0;i < songList.size();i++){
                    setSongBundle.putString("TITLE" + i, songList.get(i).getsong());
                    setSongBundle.putString("REF" + i, songList.get(i).getReference());
                }

                setSongFragment.setSongList(songList);

                setSongFragment.setArguments(setSongBundle);
                swapFragment(setSongFragment);
            }
        });
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    private void swapFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
