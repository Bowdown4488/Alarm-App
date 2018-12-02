package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MusicFragment extends Fragment {

    private RecyclerView musicRecycler;
    private MusicAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        musicRecycler = view.findViewById(R.id.musicRecycler);

        manager = new LinearLayoutManager(view.getContext());
        musicRecycler.setLayoutManager(manager);

        adapter = new MusicAdapter();
        musicRecycler.setAdapter(adapter);


        Bundle savedArgs = getArguments();
        if(savedArgs!=null){
            for (int i = 0 ;i < savedArgs.getInt("SIZE");i++){
                adapter.addItem(savedArgs.getString("TITLE"+i),savedArgs.getString("REF"+i));
            }
        }

        Log.d("--- a10_fragments","MusicFragment onViewCreated");

    }
}
