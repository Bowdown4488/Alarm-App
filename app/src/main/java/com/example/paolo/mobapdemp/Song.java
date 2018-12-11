package com.example.paolo.mobapdemp;

public class Song {

    String artistId;
    String song;
    String tag;
    String user;
    String reference;
    Boolean isChecked;

    public Song(){

    }

    public Song (String artistId, String song, String tag, String user, String reference){
        this.artistId = artistId;
        this.song = song;
        this.tag = tag;
        this.user = user;
        this.reference = reference;
    }

    public Song (String song, String reference){
        this.song = song;
        this.reference = reference;
    }

    public Song (String song){
        this.song = song;
    }

    public String getartistId() {
        return artistId;
    }

    public String getsong() {
        return song;
    }

    public String gettag() {
        return tag;
    }

    public String getUser() { return user;}

    public String getReference() { return reference;}

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
