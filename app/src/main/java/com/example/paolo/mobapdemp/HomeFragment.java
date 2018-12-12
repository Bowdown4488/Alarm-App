package com.example.paolo.mobapdemp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {

    private RecyclerView homeRecycler;
    private HomeAlarmAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private MediaPlayer mediaPlayer;
    private int size;

    private FloatingActionButton addBtn;
    List<Song> songList;
    ArrayList<String> reference = new ArrayList<>();

    public static final String ALARM_UPDATE_TAG =
            "com.example.paolo.mobapdemp.ActivityReceiver";

    private BroadcastReceiver receiver = new ActivityReceiver();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        homeRecycler = view.findViewById(R.id.homeRecycler);

        manager = new LinearLayoutManager(view.getContext());
        homeRecycler.setLayoutManager(manager);

        DividerItemDecoration dividerItemDecoration  = new DividerItemDecoration(homeRecycler.getContext(),((LinearLayoutManager) manager).VERTICAL);
        homeRecycler.setHasFixedSize(true);
        homeRecycler.setLayoutManager(manager);
        homeRecycler.addItemDecoration(dividerItemDecoration);

        adapter = new HomeAlarmAdapter(HomeFragment.this);
        homeRecycler.setAdapter(adapter);

//        Log.d("songlist", "Pass list");
//        int i = songList.size();
//        Log.d("songlist",Integer.toString(i));
//        Log.d("songlist", songList.get(0).getsong());

        addBtn = view.findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmSetFragment fragment = new AlarmSetFragment();

                Bundle savedArgs = getArguments();
                for(int i = 0;i<songList.size();i++){
                    savedArgs.putString("LIST"+i,songList.get(i).getReference());
                }

                fragment.setSongList(songList);
                swapFragment(fragment);
            }
        });
        
        Bundle savedArgs = getArguments();                  //loads data passed from main activity
        if(savedArgs!=null){
            adapter.addItem(savedArgs.getInt("HOUR"), savedArgs.getInt("MINUTE"), savedArgs.getInt("DAY"));
            size = savedArgs.getInt("Size");
            Log.d("tester","Rand: " + Integer.toString(size));
            for(int i = 0;i<size;i++){
                reference.add(savedArgs.getString("LIST"+i));
                Log.d("tester",reference.get(i));
            }
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(ALARM_UPDATE_TAG);

        Intent i = new Intent(getActivity(),ActivityReceiver.class);
        Bundle b = new Bundle();
        b.putInt("Size", size);
        for(int x = 0;x<size;x++){
            b.putString("LIST"+i, reference.get(x));
        }

        i.putExtras(b);

        getActivity().registerReceiver(receiver, filter);
        Log.d("--- a10_fragments","HomeFragment onViewCreated");

    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    private void swapFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public int getSize(){
        return this.size;
    }

    public ArrayList<String> getList(){
        return this.reference;
    }

    public class ActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            int bounds = intent.getIntExtra("Size",0);

            Random random = new Random();
            int rand = random.nextInt((bounds - 0) + 1) + 0;

            Log.d("ALARMERINO","Rand: " + Integer.toString(rand));

            ArrayList<String> listSong = new ArrayList<>();
            for(int y = 0; y < bounds ; y++){
                listSong.add(intent.getStringExtra("LIST"+y));
            }

            mediaPlayer = new MediaPlayer();
            try{
                //mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/woke-4a6eb.appspot.com/o/ReeeeeeeZero.mp3?alt=media&token=bcab51be-eec5-4ec6-be07-ac42f3f853b8");
                mediaPlayer.setDataSource(listSong.get(rand));
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {

                            }
                        });
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.prepare();
            }
            catch(IOException e){
                e.printStackTrace();
            }

            Log.d("ALARMERINO", "onReceive: BROADCAST RECEIVED");
            Toast.makeText(context,"Alarm for song:",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
