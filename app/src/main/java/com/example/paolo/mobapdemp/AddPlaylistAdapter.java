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

public class AddPlaylistAdapter extends RecyclerView.Adapter<AddPlaylistHolder> {

    private ArrayList<Song> musicList;

    public AddPlaylistAdapter(){
        musicList = new ArrayList<>();
    }

    @NonNull
    @Override
    public AddPlaylistHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.add_recycler_row, viewGroup, false);


        AddPlaylistHolder holder = new AddPlaylistHolder(view);
        Log.d("Adapter","create adapter");

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddPlaylistHolder addPlaylistHolder, int i) {
        addPlaylistHolder.setTitle(musicList.get(i).getsong());
        addPlaylistHolder.setCheckBox(false);

        Log.d("Adapter","creating holder");
    }

    @Override
    public int getItemCount() { return musicList.size(); }

    public void addItem(String title) {
        musicList.add(new Song(title));
        notifyItemInserted(musicList.size() - 1);
    }

}
