package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SetSongHolder extends RecyclerView.ViewHolder{

    private TextView titleTxt;
    private Fragment parentFragment;
    String reference;


    public SetSongHolder(@NonNull View itemView, final Fragment parentFragment) {
        super(itemView);

        titleTxt = itemView.findViewById(R.id.titleTxt);
        this.parentFragment = parentFragment;
    }

    public void setTitleTxt(String text){
        titleTxt.setText(text);
    }

    public void addItemViewListener(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
