package com.example.paolo.mobapdemp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;


public class AddPlaylistHolder extends RecyclerView.ViewHolder {

    private CheckBox checkBox;
    private TextView title;
    private String songTitle;
    List<Song> songList;

    public AddPlaylistHolder(@NonNull View itemView) {
        super(itemView);


        checkBox = itemView.findViewById(R.id.checkBox);
        title = itemView.findViewById(R.id.titleTxt);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(checkBox.isChecked()){
//                    checkBox.setChecked(false);
//                    Log.d("Current song", songTitle);
//                    for(int i = 0;i < songList.size();i++){
//                        if(songList.get(i).getsong().equals(songTitle)){
//                            songList.get(i).setChecked(false);
//                            Log.d("Song",songList.get(i).getsong() + " is checked " +  songList.get(i).getChecked());
//                        }
//                    }
//                }
              //  else{
                    checkBox.setChecked(true);
                    Log.d("Current song", songTitle);
                    for(int i = 0;i < songList.size();i++){
                        if(songList.get(i).getsong().equals(songTitle)){
                            songList.get(i).setChecked(true);
                            Log.d("Song",songList.get(i).getsong() + " is checked " +  songList.get(i).getChecked());
                        }
                    }
                //}
            }
        });
    }

    public void setTitle(String text){
        title.setText(text);
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setCheckBox(Boolean check) {
        checkBox.setChecked(check);
    }

    public void setTrue (){
        checkBox.setChecked(true);
    }

    public Boolean getCheckBox() {
        return checkBox.isChecked();
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}
