package com.example.paolo.mobapdemp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistHolder>  {

    private ArrayList<PlaylistModel> PlaylistList;

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
        PlaylistHolder.setName(PlaylistList.get(i).getPlaylistName());
        PlaylistHolder.setCreatorAndCount(PlaylistList.get(i).getCreator(),PlaylistList.get(i).getSongCount());
    }

    @Override
    public int getItemCount() {
        return PlaylistList.size();
    }

    public void addItem(String name,String creator, int count){
        PlaylistList.add(new PlaylistModel(name,creator,count));
        notifyItemInserted(PlaylistList.size()-1);
    }
}
