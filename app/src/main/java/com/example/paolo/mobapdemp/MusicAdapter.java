package com.example.paolo.mobapdemp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicHolder> {

    private ArrayList<Song> musicList;

    public MusicAdapter(){
        musicList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.music_recycler_row, viewGroup, false);


        MusicHolder holder = new MusicHolder(view);
        Log.d("Adapter","create adapter");

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder musicHolder, int i) {
        musicHolder.setSongTitle(musicList.get(i).getsong());
        musicHolder.setSongReference(musicList.get(i).getReference());
        Log.d("Adapter","creating holder");
    }

    @Override
    public int getItemCount() { return musicList.size(); }

            public void filterList(ArrayList<Song> filter){
                musicList.clear();
                musicList.addAll(filter);
                Log.d("after filter","Data added");
                notifyDataSetChanged();
            }

            public void addItem(String title, String ref) {
                musicList.add(new Song(title, ref));
                notifyItemInserted(musicList.size() - 1);
            }

        }