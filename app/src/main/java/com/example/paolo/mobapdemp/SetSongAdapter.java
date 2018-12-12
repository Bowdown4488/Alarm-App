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

    private ArrayList<Song> setSongList;
    private Fragment parentFragment;
    private int counter = 0;

    public SetSongAdapter(Fragment fragment){
        setSongList = new ArrayList<>();
        this.parentFragment = fragment;
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
        setSongHolder.setTitleTxt(setSongList.get(i).getsong());
        setSongHolder.setReference(setSongList.get(i).getReference());
        this.counter = i;
    }

    @Override
    public int getItemCount() {
        return setSongList.size();
    }

    public class ItemViewListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            AlarmSetFragment fragment = new AlarmSetFragment();
            Bundle alarmSetBundle = new Bundle();
            alarmSetBundle.putString("TITLE", setSongList.get(counter).getsong());

            fragment.setArguments(alarmSetBundle);
            swapFragment(fragment, SetSongAdapter.this.parentFragment);
        }
    }

    private void swapFragment(Fragment fragment, Fragment parentFragment){
        FragmentTransaction transaction = parentFragment.getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addItem(String title, String ref) {
        setSongList.add(new Song(title, ref));
        notifyItemInserted(setSongList.size() - 1);
    }
}
