package com.example.paolo.mobapdemp;

import java.util.ArrayList;
import java.util.List;

public class PlaylistModel {
    String playlistID;
    String playlistTitle;
    String creator;
    List<String> songID;
    int count;

    public PlaylistModel(){

    }

    public PlaylistModel(String playlistID, String playlistTitle, String creator, List<String> songID, int count){
        this.playlistID=playlistID;
        this.playlistTitle = playlistTitle;
        this.creator=creator;
        this.songID = songID;
        this.count = count;
    }

    public PlaylistModel (String playlistTitle, String creator, int count){
        this.playlistTitle = playlistTitle;
        this.creator=creator;
        this.count = count;
    }

    public String getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<String> getSongID() {
        return songID;
    }

    public void setSongID(List<String> songID) {
        this.songID = songID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String specificSongID(int i) {
        String id = songID.get(i);
        return  id;
    }
}
