package com.example.paolo.mobapdemp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String email;
    private EditText musicSearch;

    private ActionBar toolbar;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        musicSearch = findViewById(R.id.musicSearch);

        //pul db data and put inside bundle
        databaseReference = FirebaseDatabase.getInstance().getReference("Song");
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

        songList = new ArrayList<>();

        HomeFragment fragment = new HomeFragment();                                     //defaults to HomeFragment at the start
        Bundle bundle = new Bundle();
        bundle.putString("TIME", "ded");
        bundle.putString("DAY", "getsuyoubi");
        fragment.setArguments(bundle);
        loadFragment(fragment);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        toolbar.setTitle("Home");

                        HomeFragment homeFragment = new HomeFragment();                       //put data to show here
                        Bundle homeBundle = new Bundle();
                        homeBundle.putString("TIME", "ded");
                        homeBundle.putString("DAY", "getsuyoubi");

                        homeFragment.setArguments(homeBundle);
                        loadFragment(homeFragment);
                        return true;
                    case R.id.nav_music:
                        toolbar.setTitle("Music");

                        MusicFragment musicFragment = new MusicFragment();                       //put data to show here
                        Bundle musicBundle = new Bundle();

//                        musicSearch.addTextChangedListener(new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                            }
//
//                            @Override
//                            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                            }
//
//                            @Override
//                            public void afterTextChanged(Editable s) {
//                                filter(s.toString());
//                            }
//                        });

                        for(int i = 0;i < songList.size();i++){
                            musicBundle.putString("TITLE" + i, putTitle(i));
                            musicBundle.putString("REF" + i, putReference(i));
                        }
                        musicBundle.putInt("SIZE",songList.size());

                        musicFragment.setArguments(musicBundle);
                        loadFragment(musicFragment);
                        return true;
                    case R.id.nav_user:
                        toolbar.setTitle("User");

                        UserFragment userFragment = new UserFragment();                       //put data to show here
                        Bundle userBundle = new Bundle();
                        userBundle.putString("USERNAME", "Emiru");

                        userFragment.setArguments(userBundle);
                        loadFragment(userFragment);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //if(songList.size() > 0){
                    songList.clear();
                //}
                for(DataSnapshot songSnapshot: dataSnapshot.getChildren()){
                    Song song = songSnapshot.getValue(Song.class);
                    Log.d("Song",song.getsong());
                    songList.add(song);
                }
                Log.d("Song",Integer.toString(songList.size()));
//
//                SongList adapter = new SongList(MainActivity.this,songList);
//                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void filter (String text){
//        ArrayList<MusicModel> filter = new ArrayList<>();
//        for(MusicModel song: songList){
//            if(song.getSongTitle().toLowerCase().contains(text.toLowerCase())){
//                filter.add(song);
//            }
//        }
//        MusicAdapter.filterList(filter);
//    }

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

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
