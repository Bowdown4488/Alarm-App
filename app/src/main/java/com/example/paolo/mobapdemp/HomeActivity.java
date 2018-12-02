package com.example.paolo.mobapdemp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.app.AlarmManager;
import android.app.PendingIntent;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.os.Looper.prepare;

public class HomeActivity extends AppCompatActivity {

    private TextView getEmail;
    private Button addBtn;
    private EditText saveSong;
    private EditText savetag;
    private Button stopBtn;
    private Button saveBtn;
    private ListView listView;
    List<Song> songList;
    private String email;
    private String reference;

    private int length = 0;
    private boolean snooze = false;

//    public static final String UI_UPDATE_TAG =
//            "com.example.paolo.mobapdemp.HomeActivity.ActivityReceiver";
//    private BroadcastReceiver receiver = new ActivityReceiver();
//    private int jobID;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseReference = FirebaseDatabase.getInstance().getReference("Song");
        firebaseAuth = FirebaseAuth.getInstance();

        getEmail = findViewById(R.id.getEmail);
        addBtn = findViewById(R.id.addBtn);
        saveSong = findViewById(R.id.saveSong);
        savetag = findViewById(R.id.saveTag);
        stopBtn = findViewById(R.id.stopBtn);
        saveBtn = findViewById(R.id.saveBtn);
        listView = findViewById(R.id.listViewSong);

        songList = new ArrayList<>();
        reference = "";
        //reference = "https://firebasestorage.googleapis.com/v0/b/woke-4a6eb.appspot.com/o/Hikaru%20Nara.mp3?alt=media&token=29ca2bd8-a937-4356-837c-d015ae7f9340";
        //reference = "https://firebasestorage.googleapis.com/v0/b/woke-4a6eb.appspot.com/o/ReeeeeeeZero.mp3?alt=media&token=bcab51be-eec5-4ec6-be07-ac42f3f853b8";
        //reference = "https://firebasestorage.googleapis.com/v0/b/woke-4a6eb.appspot.com/o/Catch%20The%20Moment.mp3?alt=media&token=e90fbbef-ba35-4eba-b59e-236d3b17f221";
        //reference = "https://firebasestorage.googleapis.com/v0/b/woke-4a6eb.appspot.com/o/GSG.mp3?alt=media&token=21bfcc93-ac13-43f4-8c81-c85aac802e24";
        //reference = "https://firebasestorage.googleapis.com/v0/b/woke-4a6eb.appspot.com/o/Signal.mp3?alt=media&token=2996fec0-f473-4a10-85a9-ec390ca05e57";

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

//        jobID = 1;

        if(user != null){
            getEmail.setText("Account: " + email);
            Log.d("ID",user.getUid());
            Toast.makeText(HomeActivity.this,"Unique User ID: " + user.getUid(), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(HomeActivity.this,"No use signed in", Toast.LENGTH_LONG).show();
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keepPlaying();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sing = saveSong.getText().toString().trim();
                String tag = savetag.getText().toString().trim();

                if(!TextUtils.isEmpty(sing)){
                    String id = databaseReference.push().getKey();
                    Song song = new Song(id,sing,tag,email,reference);

                    Log.d("reference",reference);
                    databaseReference.child(id).setValue(song);
                    Toast.makeText(HomeActivity.this,id, Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(HomeActivity.this,"Please Enter Details", Toast.LENGTH_LONG).show();
                }
            }
        });
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(UI_UPDATE_TAG);
//        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                songList.clear();

                for(DataSnapshot songSnapshot: dataSnapshot.getChildren()){
                    Song song = songSnapshot.getValue(Song.class);

                    songList.add(song);
                }

                SongList adapter = new SongList(HomeActivity.this,songList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void keepPlaying(){
        mediaPlayer = new MediaPlayer();
        try{
            Log.d("Len",Integer.toString(length));
            mediaPlayer.setDataSource(reference);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    length = 0;
                                }
                            });
                    mediaPlayer.seekTo(length);
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepare();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void stopPlaying(){
        if (mediaPlayer.isPlaying()){
            try{
//                mediaPlayer.reset();
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                snooze = true;
                mediaPlayer.pause();
                length = mediaPlayer.getCurrentPosition();
                int temp = length;
                temp = length / 1000;
                int milli = length;
                milli = length/10;
                Toast.makeText(HomeActivity.this,"Length Paused: " + temp + "." + milli, Toast.LENGTH_LONG).show();
                Log.d("Pause Len",Integer.toString(length));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

//    public void triggerAlarm(View view){
//        Log.d("ALARM AND BROADCAST","SignupActivity triggerAlarm "+jobID);
//
//        Intent alarmIntent = new Intent(UI_UPDATE_TAG);
//        alarmIntent.putExtra("ID", jobID);
//        jobID++;
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
//                1000000+jobID, alarmIntent, 0);
//
//        int time = 10 * 1000;
//
//        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, pendingIntent);
//    }
//
//    public class ActivityReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d("ALARM AND BROADCAST","ActivityReceiver onReceive");
//            int id = intent.getExtras().getInt("ID");
//            triggerAlarm(listView);
//        }
//    }

}
