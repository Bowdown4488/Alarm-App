package com.example.ashenone.woke;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MusicAdapter extends RecyclerView.Adapter<MusicHolder> {

    private ArrayList<MusicModel> musicList;

    public MusicAdapter(){
        musicList = new ArrayList<>();


    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.music_recycler_row, viewGroup, false);


        MusicHolder holder = new MusicHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder musicHolder, int i) {
        musicHolder.setSongTitle(musicList.get(i).getSongTitle());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public void addItem(String title){
        musicList.add(new MusicModel(title));
        notifyItemInserted(musicList.size()-1);
    }
}
