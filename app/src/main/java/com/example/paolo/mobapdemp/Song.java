package com.example.paolo.mobapdemp;

public class Song {

    String artistId;
    String song;
    String tag;
    String user;
    String reference;

    public Song(){

    }

    public Song (String artistId, String song, String tag, String user, String reference){
        this.artistId = artistId;
        this.song = song;
        this.tag = tag;
        this.user = user;
        this.reference = reference;
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
}
