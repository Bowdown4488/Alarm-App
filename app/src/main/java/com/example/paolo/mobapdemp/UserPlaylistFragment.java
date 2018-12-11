package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserPlaylistFragment extends Fragment {

    private RecyclerView UPRecycler;
    private PlaylistAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userplaylist, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        UPRecycler = view.findViewById(R.id.userPlaylistRecycler);

        manager = new LinearLayoutManager(view.getContext());
        UPRecycler.setLayoutManager(manager);

        adapter = new PlaylistAdapter();
        UPRecycler.setAdapter(adapter);

        Bundle savedArgs = getArguments();                  //loads data passed from main activity
        if(savedArgs!=null){
            for (int i = 0 ;i < savedArgs.getInt("Size");i++){
                adapter.addItem(savedArgs.getString("NAME"+i), savedArgs.getString("CREATOR"+i),savedArgs.getInt("COUNT"+i));
            }
        }
    }

}
