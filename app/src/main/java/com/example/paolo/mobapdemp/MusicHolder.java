package com.example.paolo.mobapdemp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MusicHolder extends RecyclerView.ViewHolder {

    private ImageButton playBtn;
    private ImageButton downloadBtn;
    private TextView titleTxt;

    public MusicHolder(@NonNull View itemView) {
        super(itemView);

        playBtn = itemView.findViewById(R.id.playBtn);
        downloadBtn = itemView.findViewById(R.id.downloadBtn);
        titleTxt = itemView.findViewById(R.id.titleTxt);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


}
