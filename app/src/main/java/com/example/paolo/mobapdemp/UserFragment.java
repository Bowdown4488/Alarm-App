package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserFragment extends Fragment {
    private TextView  username;
    private Button Playlistbtn;
    private Button Addlistbtn;
    List<Song> songList;

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
        Playlistbtn= view.findViewById(R.id.viewPlaybtn);
        Addlistbtn = view.findViewById(R.id.addPlaybtn);

        Playlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPlaylistFragment UPFragment = new UserPlaylistFragment();                       //put data to show here
                Bundle homeBundle = new Bundle();
                homeBundle.putString("NAME", "ded");
                homeBundle.putString("CREATOR", "MERT");
                homeBundle.putInt("SONG", 69);

                UPFragment.setArguments(homeBundle);
                loadFragment(UPFragment, UserFragment.this);
            }
        });

        Addlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                MusicFragment musicFragment = new MusicFragment();                       //put data to show here
//                Bundle musicBundle = new Bundle();
//
//                for(int i = 0;i < songList.size();i++){
//                    musicBundle.putString("TITLE" + i, putTitle(i));
//                    musicBundle.putString("REF" + i, putReference(i));
//                }
//                musicBundle.putInt("SIZE",songList.size());
//
//                musicFragment.setSongList(songList);
//                musicFragment.setArguments(musicBundle);
//                loadFragment(musicFragment, UserFragment.this);

                PlatlistFragment platlistFragment = new PlatlistFragment();
                Bundle addBundle = new Bundle();

                for(int i = 0;i < songList.size();i++){
                    addBundle.putString("TITLE" + i, putTitle(i));
                }

                addBundle.putInt("SIZE",songList.size());

                platlistFragment.setSongList(songList);
                platlistFragment.setArguments(addBundle);
                loadFragment(platlistFragment,UserFragment.this);

                Log.d("start","Playlist start");
            }
        });

        Bundle savedAgrs = getArguments();
        if(savedAgrs!=null){

//            pic.setImageResource(savedAgrs.getInt("pic"));
              username.setText(savedAgrs.getString("USERNAME"));
//            number.setText(savedAgrs.getString("number"));
        }

        Log.d("--- a10_fragments","ContactRowFragment onViewCreated");

    }
    private void loadFragment(Fragment fragment, Fragment currentFragment) {
        FragmentTransaction transaction = currentFragment.getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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

    public void setList (List<Song> list){
        songList = list;
    }
}
