package com.class_sync;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.class_sync.UserFragments.HelpDeskFragment;


public class UserFragment extends Fragment {
    CardView logout,requestOnlineCourse;
    TextView t1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_user, container, false);
        logout = root.findViewById(R.id.logout_btn);
        requestOnlineCourse = root.findViewById(R.id.userProfile_RequestOnlineCourse);
        t1 = root.findViewById(R.id.userProfileName);
        HomeScreen.bottomNavigation.show(4, true);
        t1.setText(HomeScreen.User_Name);
        requestOnlineCourse.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame,new HelpDeskFragment()).commit();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.sharedPreferencesEditor.clear().commit();
                startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().finish();
            }
        });
        return root;
    }
}