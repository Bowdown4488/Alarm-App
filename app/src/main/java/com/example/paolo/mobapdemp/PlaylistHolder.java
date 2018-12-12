package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlaylistHolder extends RecyclerView.ViewHolder {

    private TextView nameTxt;
    private TextView creatorNcountTxt;
    private ConstraintLayout layout;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private List<PlaylistModel> playList;
    private List<Song> songList;
    private String title;
    String email;

    public PlaylistHolder(@NonNull View itemView) {
        super(itemView);

        nameTxt = itemView.findViewById(R.id.pnameTxt);
        creatorNcountTxt=itemView.findViewById(R.id.creatorNcountTxt);
        layout=itemView.findViewById(R.id.layout);

        //songList = new ArrayList<>();

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                databaseReference = FirebaseDatabase.getInstance().getReference("Song");
//                firebaseAuth = FirebaseAuth.getInstance();
//
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                email = user.getEmail();

                //initSongs();

                int k = 0;
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                MusicFragment musicFragment = new MusicFragment();                       //put data to show here
                Bundle musicBundle = new Bundle();

                Log.d("Song","before init");

                for(int i = 0;i < playList.size();i++){
                    Log.d("Title","Title: " + title);
                    if(title.equals(playList.get(i).getPlaylistTitle())){
                        Log.d("Title","playlist: " + playList.get(i).getPlaylistTitle());
                        for(int j =0; j < playList.size();j++){
                            Log.d("Title","playlist id: " + playList.get(j).getSongID()); //line of null pionter
                            Log.d("Title","songlist id: " + songList.get(j).getartistId());
                            if(playList.get(i).getSongID().get(j).equals(songList.get(j).getartistId())) {
                                Log.d("Title","songlist: " + songList.get(j).getsong());
                                musicBundle.putString("TITLE" + i, songList.get(i).getsong());
                                musicBundle.putString("REF" + i, songList.get(i).getReference());
                                k++;
                            }
                        }
                    }
                }
                musicBundle.putInt("SIZE",k);

                musicFragment.setSongList(songList);
                musicFragment.setArguments(musicBundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, musicFragment).addToBackStack(null).commit();
                //loadFragment(musicFragment);
            }
        });
    }

    public List<Song> setSongList (List<Song> songList){
        return this.songList = songList;
    }

    public  void initSongs(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Song","start init");
//                songList.clear();
                for(DataSnapshot songSnapshot: dataSnapshot.getChildren()){
                    Song song = songSnapshot.getValue(Song.class);
                    songList.add(song);
                }
                Log.d("Song",Integer.toString(songList.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    public String putTitle(int i){
//        String title;
//        title = songList.get(i).getsong();
//        return title;
//    }
//
//    public String putReference(int i){
//        String ref;
//        ref = songList.get(i).getReference();
//        return ref;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String text){
        nameTxt.setText(text);
    }

    public void setCreator (String text,int count){
        creatorNcountTxt.setText("By "+text+" ~ "+ count +" songs");
    }

    public void setPlayList (List<PlaylistModel> playList){
        this.playList = playList;
    }

//    private void loadFragment(Fragment fragment) {
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

}
