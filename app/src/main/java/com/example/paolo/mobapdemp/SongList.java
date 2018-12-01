package com.example.paolo.mobapdemp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SongList extends ArrayAdapter<Song> {

    Activity context;
    List<Song> songList;

    public SongList(Activity context, List<Song> songList){
        super(context,R.layout.list_layout, songList);
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();

        View listViewItem = layoutInflater.inflate(R.layout.list_layout,null,true);

        TextView songName = listViewItem.findViewById(R.id.songView);
        TextView genreName= listViewItem.findViewById(R.id.genreView);

        Song song = songList.get(position);

        songName.setText("Song: " + song.getsong());
        genreName.setText("Genre:" + song.gettag());

        return listViewItem;

    }
}
