package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SetSongAdapter extends RecyclerView.Adapter<SetSongHolder> {

    private ArrayList<SetSongModel> setSongList;
    private Fragment parentFragment;
    private int counter = 0;

    public SetSongAdapter(Fragment fragment){
        setSongList = new ArrayList<>();

    }

    @NonNull
    @Override
    public SetSongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.setsong_recycler_row, viewGroup, false);

        SetSongHolder holder = new SetSongHolder(view, this.parentFragment);
        holder.addItemViewListener(new SetSongAdapter.ItemViewListener());

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SetSongHolder setSongHolder, int i) {
        setSongHolder.setTitleTxt(setSongList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return setSongList.size();
    }

    public void addItem(String title){
        setSongList.add(new SetSongModel(title));
        notifyItemInserted(setSongList.size()-1);
    }

    public class ItemViewListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            SetSongFragment fragment = new SetSongFragment();
            Bundle homeBundle = new Bundle();
            homeBundle.putString("TITLE", setSongList.get(counter).getTitle());

            fragment.setArguments(homeBundle);
            swapFragment(fragment, SetSongAdapter.this.parentFragment);
        }
    }

    private void swapFragment(Fragment fragment, Fragment parentFragment){
        FragmentTransaction transaction = parentFragment.getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
