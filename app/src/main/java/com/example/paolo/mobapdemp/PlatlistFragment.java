package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class PlatlistFragment extends Fragment{
    private RecyclerView addRecycler;
    private AddPlaylistAdapter adapter;
    private RecyclerView.LayoutManager manager;
    List<Song> songList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        addRecycler = view.findViewById(R.id.addRecycler);

        manager = new LinearLayoutManager(view.getContext());
        addRecycler.setLayoutManager(manager);

        adapter = new AddPlaylistAdapter();
        addRecycler.setAdapter(adapter);

        Bundle savedArgs = getArguments();
        if(savedArgs!=null){
            for (int i = 0 ;i < savedArgs.getInt("SIZE");i++){
                adapter.addItem(savedArgs.getString("TITLE"+i));
            }
        }
        Log.d("--- a10_fragments","AddFragment onViewCreated");
        Log.d("toFilter","On created");

    }

    public List<Song> setSongList (List<Song> songList){
        return this.songList = songList;
    }


}
