package com.example.paolo.mobapdemp;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class HomeAlarmHolder extends RecyclerView.ViewHolder {

    private TextView timeTxt;
    private Switch alarmSwitch;
    private Fragment parentFragment;
    public HomeAlarmHolder(@NonNull final View itemView, Fragment parentFragment) {
        super(itemView);

        timeTxt = itemView.findViewById(R.id.timeTxt);
        alarmSwitch = itemView.findViewById(R.id.alarmSwitch);
        this.parentFragment = parentFragment;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(new AlarmSetFragment(), HomeAlarmHolder.this.parentFragment);
            }
        });
    }

    public void setTimeTxt(String text){
        timeTxt.setText(text);
    }

    public void setAlarmSwitch(boolean active){
        alarmSwitch.setChecked(active);
    }

    private void swapFragment(Fragment fragment, Fragment parentFragment){
        FragmentTransaction transaction = parentFragment.getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
