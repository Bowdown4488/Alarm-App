package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlatlistFragment extends Fragment{
    private RecyclerView addRecycler;
    private AddPlaylistAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private EditText playlistTitle;
    private Button confirmBtn;
    private String email;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    ListStorage listStorage;

    List<Song> songList;
    List<Song> addPlaylist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        playlistTitle = view.findViewById(R.id.playlistTitle);
        confirmBtn = view.findViewById(R.id.confirmBtn);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        addRecycler = view.findViewById(R.id.addRecycler);

        manager = new LinearLayoutManager(view.getContext());
        addRecycler.setLayoutManager(manager);

        adapter = new AddPlaylistAdapter();
        addRecycler.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Playlist");
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

        Bundle savedArgs = getArguments();
        if(savedArgs!=null){
            adapter.setStorage(listStorage);
            for (int i = 0 ;i < savedArgs.getInt("SIZE");i++){
                adapter.addItem(savedArgs.getString("TITLE"+i));
            }
        }
        Log.d("--- a10_fragments","AddFragment onViewCreated");
        Log.d("toFilter","On created");

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listStorage.lookSongs();

                //Do database saving here
                savePlaylist();

                //reset boolean to false for next checking
                listStorage.resetCheck();
                listStorage.lookSongs();

                //Navigate back to fragment after adding playlist to database
                UserFragment userFragment = new UserFragment();                       //put data to show here
                Bundle userBundle = new Bundle();
                userBundle.putString("USERNAME", email);

                userFragment.setList(listStorage.getSongList());
                userFragment.setStorage(listStorage);

                userFragment.setArguments(userBundle);
                loadFragment(userFragment, PlatlistFragment.this);

                String title = playlistTitle.getText().toString();
                Toast.makeText(getActivity().getApplicationContext(),"Playlist " + title + " has been added",Toast.LENGTH_LONG).show();
            }
        });

    }

    public List<Song> setSongList (List<Song> songList){
        return this.songList = songList;
    }

    public void setStorage (ListStorage listStorage){
        this.listStorage = listStorage;
    }


    private void loadFragment(Fragment fragment, Fragment currentFragment) {
        FragmentTransaction transaction = currentFragment.getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void savePlaylist (){
        String title = playlistTitle.getText().toString().trim();
        addPlaylist = listStorage.getSongList();
        List<String> songID = new ArrayList<String>();
        int j = 0;

        for(int i = 0;i < addPlaylist.size();i++){
            if(addPlaylist.get(i).getChecked() == true){
                songID.add(addPlaylist.get(i).getartistId());
                j++;
                Log.d("ID","Song: " + addPlaylist.get(i).getsong() + " ID:" + addPlaylist.get(i).getartistId());
            }
        }
        String id = databaseReference.push().getKey();
        PlaylistModel playlistModel = new PlaylistModel(id,title,email,songID);

        databaseReference.child(id).setValue(playlistModel);
    }

}
