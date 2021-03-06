package com.example.paolo.mobapdemp;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class SetSongFragment extends Fragment {

    private RecyclerView setSongRecycler;
    private SetSongAdapter adapter;
    private RecyclerView.LayoutManager manager;

    List<Song> songList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setsong, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setSongRecycler = view.findViewById(R.id.setSongRecycler);

        manager = new LinearLayoutManager(view.getContext());
        setSongRecycler.setLayoutManager(manager);

        adapter = new SetSongAdapter(SetSongFragment.this);
        setSongRecycler.setAdapter(adapter);

        Bundle savedArgs = getArguments();                  //loads data passed from main activity
        if(savedArgs!=null){
            for (int i = 0 ;i < songList.size();i++){
                adapter.addItem(savedArgs.getString("TITLE"+i),savedArgs.getString("REF"+i));
            }
        }
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

}
