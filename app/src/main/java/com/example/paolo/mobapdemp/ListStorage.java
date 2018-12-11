package com.example.paolo.mobapdemp;

import android.util.Log;

import java.util.List;

public class ListStorage {

    List<Song> songList;

    public ListStorage(List<Song> songList){
        this.songList = songList;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void lookSongs (){
        for(int i = 0;i < songList.size();i++){
            Log.d("List",songList.get(i).getsong() + " is checked " +  songList.get(i).getChecked());
        }
    }

    public int size (){
        return songList.size();
    }

    public void resetCheck (){
        for(int i = 0;i < songList.size();i++){
           if(songList.get(i).getChecked() == true){
               songList.get(i).setChecked(false);
           }
        }
    }


}
