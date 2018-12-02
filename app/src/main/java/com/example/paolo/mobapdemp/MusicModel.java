package com.example.paolo.mobapdemp;

public class MusicModel {

    private boolean isPlaying;
    private boolean isDownloaded;
    private String songTitle;

    public MusicModel(String title){
        this.songTitle = title;
    }

    public String getSongTitle(){
        return this.songTitle;
    }

    public void setSongTitle(String title){
        this.songTitle = title;
    }
}
