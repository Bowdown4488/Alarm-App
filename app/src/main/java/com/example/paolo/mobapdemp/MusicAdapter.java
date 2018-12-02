package com.example.paolo.mobapdemp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        musicHolder.setSongReference(musicList.get(i).getReference());
    }

//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    filter = musicList;
//                } else {
//                    ArrayList<MusicModel> filteredList = new ArrayList<>();
//                    for (MusicModel row : musicList) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        if (row.getSongTitle().toLowerCase().contains(charString.toLowerCase())) {
//                            filteredList.add(row);
//                        }
//                    }
//
//                    filter = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = filter;
//                return filterResults;
//            }

            @Override
            public int getItemCount() {
                return musicList.size();
            }

            public void filterList(ArrayList<MusicModel> filter){
                musicList = filter;
                notifyDataSetChanged();
            }

            public void addItem(String title, String ref) {
                musicList.add(new MusicModel(title, ref));
                notifyItemInserted(musicList.size() - 1);
            }

        }