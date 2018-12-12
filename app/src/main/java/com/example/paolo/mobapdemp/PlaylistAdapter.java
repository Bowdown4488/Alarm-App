package com.example.paolo.mobapdemp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistHolder>  {

    private List<PlaylistModel> PlaylistList;
    List<Song> songList;

    public PlaylistAdapter(){
        PlaylistList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PlaylistHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.user_playlist_row, viewGroup, false);


        PlaylistHolder holder = new PlaylistHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistHolder PlaylistHolder, int i) {

        PlaylistHolder.setName(PlaylistList.get(i).getPlaylistTitle());
        PlaylistHolder.setCreator(PlaylistList.get(i).getCreator(),PlaylistList.get(i).getCount());
        PlaylistHolder.setTitle(PlaylistList.get(i).getPlaylistTitle());
        PlaylistHolder.setPlayList(PlaylistList);
        PlaylistHolder.setSongList(songList);

    }

    @Override
    public int getItemCount() {
        return PlaylistList.size();
    }

    public void addItem(String name,String creator,int count){
        PlaylistList.add(new PlaylistModel(name,creator,count));
        notifyItemInserted(PlaylistList.size()-1);
    }

    public List<Song> setSongList (List<Song> songList){
        return this.songList = songList;
    }

    public void setPlayList (List<PlaylistModel> playList){
        this.PlaylistList = playList;
    }

}
