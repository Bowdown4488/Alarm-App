package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UserFragment extends Fragment {
    private TextView  username;
    private Button Playlistbtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        pic = view.findViewById(R.id.contact_image);
        username = view.findViewById(R.id.userName);
//        number = view.findViewById(R.id.contact_number);
        Playlistbtn= view.findViewById(R.id.viewPlaybtn);
        //(4) The arguments sent in the activity can be extracted here. It is checked if it is
        //    null initially as the first element is manually added and does not send any
        //    arguments on the initialization of the Fragment.
        Playlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPlaylistFragment UPFragment = new UserPlaylistFragment();                       //put data to show here
                Bundle homeBundle = new Bundle();
                homeBundle.putString("NAME", "ded");
                homeBundle.putString("CREATOR", "MERT");
                homeBundle.putInt("SONG", 69);

                UPFragment.setArguments(homeBundle);
                loadFragment(UPFragment, UserFragment.this);
            }
        });
        Bundle savedAgrs = getArguments();
        if(savedAgrs!=null){

//            pic.setImageResource(savedAgrs.getInt("pic"));
              username.setText(savedAgrs.getString("USERNAME"));
//            number.setText(savedAgrs.getString("number"));
        }

        Log.d("--- a10_fragments","ContactRowFragment onViewCreated");

    }
    private void loadFragment(Fragment fragment, Fragment currentFragment) {
        FragmentTransaction transaction = currentFragment.getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
