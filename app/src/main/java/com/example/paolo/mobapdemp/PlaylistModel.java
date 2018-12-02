package com.example.paolo.mobapdemp;

public class PlaylistModel {
    private String name;
    private String creator;
    private int songCount;

    public PlaylistModel(String name,String creator, int song){

        this.name=name;
        this.creator=creator;
        songCount=song;
    }

    public String getPlaylistName(){
        return name;
    }

    public void setPlaylistName(String title){
        name = title;
    }

    public String getCreator(){
        return creator;
    }

    public void setCreator(String title){
        creator = title;
    }

    public int getSongCount(){
        return songCount;
    }

    public void setSongCount(int song){
        songCount=song;
    }


}
