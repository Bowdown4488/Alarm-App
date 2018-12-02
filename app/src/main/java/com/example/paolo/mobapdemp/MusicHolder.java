package com.example.paolo.mobapdemp;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MusicHolder extends RecyclerView.ViewHolder {

    private ImageButton playBtn;
    private ImageButton downloadBtn;
    private TextView titleTxt;
    private MediaPlayer mediaPlayer;
    private String reference;
    private int length = 0;
    private boolean isPlaying = false;

    public MusicHolder(@NonNull View itemView) {
        super(itemView);

        playBtn = itemView.findViewById(R.id.playBtn);
        downloadBtn = itemView.findViewById(R.id.downloadBtn);
        titleTxt = itemView.findViewById(R.id.titleTxt);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying == false){
                    keepPlaying();
                }
                else{
                    stopPlaying();
                }

            }
        });

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setSongTitle(String text){
        titleTxt.setText(text);
    }

    public  void setSongReference (String ref){this.reference = ref;}

    public void keepPlaying(){
        isPlaying = true;
        Log.d("Play","playing song" + reference);
        mediaPlayer = new MediaPlayer();
        try{
            Log.d("Len",Integer.toString(length));
            //mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/woke-4a6eb.appspot.com/o/ReeeeeeeZero.mp3?alt=media&token=bcab51be-eec5-4ec6-be07-ac42f3f853b8");
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

    public void stopPlaying() {
        isPlaying = false;
        Log.d("Play","stopping song" + reference);
        if (mediaPlayer.isPlaying()) {
            try {
//                mediaPlayer.reset();
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                snooze = true;
                mediaPlayer.pause();
                length = mediaPlayer.getCurrentPosition();
                int temp = length;
                temp = length / 1000;
                int milli = length;
                milli = length / 10;
                Log.d("Pause Len", Integer.toString(temp) + "." + Integer.toString(milli));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
