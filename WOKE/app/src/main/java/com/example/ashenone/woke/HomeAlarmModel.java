package com.example.ashenone.woke;

public class HomeAlarmModel {

    private String time;
    private String day;
    private boolean active;

    public HomeAlarmModel(String time, String day){
        this.time = time;
        this.day = day;
        this.active = false;
    }

    public String getAlarmTime(){
        return this.time;
    }

    public void setAlarmTime(String alarmTime){
        this.time = alarmTime;
    }

    public String getAlarmDay(){
        return this.day;
    }

    public void setAlarmDay(String alarmDay){
        this.day = alarmDay;
    }

    public boolean isActive(){
        return this.active;
    }

    public void setActive(){
        this.active = true;
    }

    public void setInactive(){
        this.active = false;
    }
}
