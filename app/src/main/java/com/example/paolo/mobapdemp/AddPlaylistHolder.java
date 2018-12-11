package com.example.paolo.mobapdemp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


public class AddPlaylistHolder extends RecyclerView.ViewHolder {

    private CheckBox checkBox;
    private TextView title;

    public AddPlaylistHolder(@NonNull View itemView) {
        super(itemView);

        checkBox = itemView.findViewById(R.id.checkBox);
        title = itemView.findViewById(R.id.titleTxt);

    }

    public void setTitle(String text){
        title.setText(text);
    }

    public void setCheckBox(Boolean check) {
        checkBox.setChecked(check);
    }
}
