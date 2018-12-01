package com.example.paolo.mobapdemp;

public class Song {

    String artistId;
    String song;
    String tag;

    public Song(){

    }

    public Song (String artistId, String song, String tag){
        this.artistId = artistId;
        this.song = song;
        this.tag = tag;
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
}
