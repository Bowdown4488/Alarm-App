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

public class MusicFragment extends Fragment {

    private RecyclerView musicRecycler;
    private MusicAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private EditText e;
    private MainActivity act;
    List<Song> songList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        this.e = view.findViewById(R.id.musicSearch);
        Log.d("toFilter","Initializing edit text");
        return view;
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
        Log.d("toFilter","On created");

        try{ //check where to put in the music fragment creation
            Log.d("in try","Filter edit text");
            this.getE().addTextChangedListener(new TextWatcher() { //LINE OF NULL POINTER
                @Override

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d("in try","After null pointer");
                    filter(s.toString());
                    Log.d("Filter done","Filter Finish");
                }
            });
        }catch(NullPointerException e){
            e.printStackTrace();
        }

    }

    public void filter (String text){
        ArrayList<Song> filter = new ArrayList<>();
        for(Song song: songList){
            if(song.getsong().toLowerCase().contains(text.toLowerCase())){
                filter.add(song);
            }
        }

//        if(filter.get(0).getsong() != null ){  Log.d("list",filter.get(0).getsong());
//        }

        Log.d("toFilter",text);
        adapter.filterList(filter); //Fix null pointer in this line
    }

    public List<Song> setSongList (List<Song> songList){
        return this.songList = songList;
    }

    public EditText getE() {
        return e;
    }
}
