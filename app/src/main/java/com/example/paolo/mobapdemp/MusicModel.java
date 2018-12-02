package com.example.paolo.mobapdemp;

public class MusicModel {

    private boolean isPlaying;
    private boolean isDownloaded;
    private String songTitle;
    private String reference;

    public MusicModel(String title, String reference){
        this.songTitle = title;
        this.reference=reference;
    }

    public String getSongTitle(){
        return this.songTitle;
    }

    public void setSongTitle(String title){
        this.songTitle = title;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
