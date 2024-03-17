package com.class_sync.UserFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.class_sync.HomeScreen;
import com.class_sync.R;
import com.class_sync.SendEmail;



public class HelpDeskFragment extends Fragment {
EditText editText;
LottieAnimationView lottieAnimationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_help_desk, container, false);
        editText=root.findViewById(R.id.HelpDeskEditText);
        lottieAnimationView=root.findViewById(R.id.HelpDeskSubmitBtn);
        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottieAnimationView.playAnimation();
                SendEmail sendEmail=new SendEmail(HomeScreen.User_Email,HomeScreen.User_Name,"",editText.getText().toString(),1);
            }
        });
        return root;
    }
}