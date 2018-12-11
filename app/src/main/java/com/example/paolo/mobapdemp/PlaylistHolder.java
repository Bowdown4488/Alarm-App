package com.example.paolo.mobapdemp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PlaylistHolder extends RecyclerView.ViewHolder {

    private TextView nameTxt;
    private TextView creatorNcountTxt;

    public PlaylistHolder(@NonNull View itemView) {
        super(itemView);

        nameTxt = itemView.findViewById(R.id.pnameTxt);
        creatorNcountTxt=itemView.findViewById(R.id.creatorNcountTxt);



    }

    public void setName(String text){
        nameTxt.setText(text);
    }

    public void setCreator (String text,int count){
        creatorNcountTxt.setText("By "+text+" ~ "+ count +" songs");
    }


}
