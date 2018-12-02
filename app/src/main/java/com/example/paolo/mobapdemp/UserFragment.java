package com.example.paolo.mobapdemp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        pic = view.findViewById(R.id.contact_image);
//        name = view.findViewById(R.id.contact_name);
//        number = view.findViewById(R.id.contact_number);

        //(4) The arguments sent in the activity can be extracted here. It is checked if it is
        //    null initially as the first element is manually added and does not send any
        //    arguments on the initialization of the Fragment.
        Bundle savedAgrs = getArguments();
        if(savedAgrs!=null){
//            pic.setImageResource(savedAgrs.getInt("pic"));
//            name.setText(savedAgrs.getString("name"));
//            number.setText(savedAgrs.getString("number"));
        }

        Log.d("--- a10_fragments","ContactRowFragment onViewCreated");

    }
}
