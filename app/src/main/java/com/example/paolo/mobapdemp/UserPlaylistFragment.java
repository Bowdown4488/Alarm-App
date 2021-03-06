package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class UserPlaylistFragment extends Fragment {

    private RecyclerView UPRecycler;
    private PlaylistAdapter adapter;
    private RecyclerView.LayoutManager manager;
    List<Song> songList;
    List<PlaylistModel> playList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userplaylist, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        UPRecycler = view.findViewById(R.id.userPlaylistRecycler);

        manager = new LinearLayoutManager(view.getContext());

        DividerItemDecoration dividerItemDecoration  = new DividerItemDecoration(UPRecycler.getContext(),((LinearLayoutManager) manager).VERTICAL);
        UPRecycler.setHasFixedSize(true);
        UPRecycler.setLayoutManager(manager);
        UPRecycler.addItemDecoration(dividerItemDecoration);

        UPRecycler.setLayoutManager(manager);

        adapter = new PlaylistAdapter();
        UPRecycler.setAdapter(adapter);

        Bundle savedArgs = getArguments();                  //loads data passed from main activity
        if(savedArgs!=null){

            for (int i = 0 ;i < savedArgs.getInt("Size");i++){
                adapter.addItem(savedArgs.getString("NAME"+i), savedArgs.getString("CREATOR"+i),savedArgs.getInt("COUNT"+i));
            }
            adapter.setSongList(songList);
            adapter.setPlayList(playList);
        }
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public void setPlayList (List<PlaylistModel> playList){
        this.playList = playList;
    }

}
