package com.example.paolo.mobapdemp;

public class HomeAlarmModel {

    private int hour;
    private int minute;
    private int day;
    private boolean active;

    public HomeAlarmModel(int hour, int minute, int day /*Song song*/){
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.active = false;
    }

    public int getAlarmHour(){
        return this.hour;
    }

    public void setAlarmHour(int alarmHour){
        this.hour = alarmHour;
    }

    public int getAlarmMinute(){
        return this.minute;
    }

    public void setAlarmMinute(int alarmMinute){
        this.minute = alarmMinute;
    }

    public int getAlarmDay(){
        return this.day;
    }

    public void setAlarmDay(int alarmDay){
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
