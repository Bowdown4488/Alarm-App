package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    private TextView username;
    private Button Playlistbtn;
    private Button Addlistbtn;
    private DatabaseReference databaseReference;
    String email;
    List<Song> songList;
    List<PlaylistModel> playList;
    ListStorage listStorage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        pic = view.findViewById(R.id.contact_image);
        username = view.findViewById(R.id.userName);
//        number = view.findViewById(R.id.contact_number);
        Playlistbtn = view.findViewById(R.id.viewPlaybtn);
        Addlistbtn = view.findViewById(R.id.addPlaybtn);

        playList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Playlist");

        loadPlaylist();



        Playlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPlaylistFragment UPFragment = new UserPlaylistFragment();                       //put data to show here
                Bundle homeBundle = new Bundle();

                for(int i = 0;i < playList.size();i++){
                    homeBundle.putString("NAME" + i, putPlaylist(i));
                    homeBundle.putString("CREATOR" + i, putOwner(i));
                    homeBundle.putInt("COUNT" + i, putCount(i));
                }

                homeBundle.putInt("Size", playList.size());
                UPFragment.setPlayList(playList);
                UPFragment.setSongList(songList);

                UPFragment.setArguments(homeBundle);
                loadFragment(UPFragment, UserFragment.this);
            }
        });

        Addlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlatlistFragment platlistFragment = new PlatlistFragment();
                Bundle addBundle = new Bundle();

                for (int i = 0; i < listStorage.size(); i++) {
                    addBundle.putString("TITLE" + i, putTitle(i));
                }

                addBundle.putInt("SIZE", listStorage.size());

                platlistFragment.setSongList(listStorage.getSongList());
                platlistFragment.setStorage(listStorage);
                platlistFragment.setArguments(addBundle);
                loadFragment(platlistFragment, UserFragment.this);

                Log.d("start", "Playlist start");
            }
        });

        Bundle savedAgrs = getArguments();
        if (savedAgrs != null) {

//            pic.setImageResource(savedAgrs.getInt("pic"));
            username.setText(savedAgrs.getString("USERNAME"));
//            number.setText(savedAgrs.getString("number"));
        }

        Log.d("--- a10_fragments", "ContactRowFragment onViewCreated");

    }

    private void loadFragment(Fragment fragment, Fragment currentFragment) {
        FragmentTransaction transaction = currentFragment.getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public String putPlaylist(int i) {
        String title;
        title = playList.get(i).getPlaylistTitle();
        return title;
    }

    public String putOwner(int i) {
        String owner;
        owner = playList.get(i).getCreator();
        return owner;
    }

    public int putCount (int i){
        int count;
        count = playList.get(i).getCount();
        return count;
    }

    public String putTitl(int i) {
        String owner;
        owner = playList.get(i).getPlaylistTitle();
        return owner;
    }

//    public ArrayList<PlaylistModel> putList(){
//        List<String> test = new ArrayList<>();
//        for(int i =0; i<playList.size();i++){
//            test.add(playList.get(i).getSongID());
//        }
//    }

    public void setList(List<Song> list) {
        songList = list;
    }

    public void setStorage(ListStorage listStorage) {
        this.listStorage = listStorage;
    }


    public String putTitle(int i){
        String title;
        title = songList.get(i).getsong();
        return title;
    }

    public String putReference(int i){
        String ref;
        ref = songList.get(i).getReference();
        return ref;
    }

    public void loadPlaylist() {
        Log.d("Playlist","Start Loading from db");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //playList.clear();

                for (DataSnapshot playListSnapshot : dataSnapshot.getChildren()) {

                    PlaylistModel playlistModel = playListSnapshot.getValue(PlaylistModel.class);
                    playList.add(playlistModel);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
