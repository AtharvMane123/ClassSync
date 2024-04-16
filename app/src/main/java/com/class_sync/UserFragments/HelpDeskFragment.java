package com.class_sync.UserFragments;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.class_sync.HomeScreen;
import com.class_sync.R;
import com.class_sync.SendEmail;



public class HelpDeskFragment extends Fragment {
EditText editText;
public static int mode;
TextView textView;
LottieAnimationView lottieAnimationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_help_desk, container, false);
        editText=root.findViewById(R.id.HelpDeskEditText);
        textView=root.findViewById(R.id.HelpDeskText);
        lottieAnimationView=root.findViewById(R.id.HelpDeskSubmitBtn);
       if(mode == 1){

           textView.setText("Request For an Ebook ");
           editText.setHint("Enter the name of the Ebook you want");

           lottieAnimationView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   lottieAnimationView.playAnimation();
                   SendEmail sendEmail=new SendEmail(HomeScreen.User_Email,HomeScreen.User_Name,"",editText.getText().toString(),1);
               }
           });
           lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
               @Override
               public void onAnimationStart(@NonNull Animator animator) {

               }

               @Override
               public void onAnimationEnd(@NonNull Animator animator) {
                editText.setText("");
               }

               @Override
               public void onAnimationCancel(@NonNull Animator animator) {

               }

               @Override
               public void onAnimationRepeat(@NonNull Animator animator) {

               }
           });

       } else if (mode == 2)
        {

            lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lottieAnimationView.playAnimation();
                    SendEmail sendEmail=new SendEmail(HomeScreen.User_Email,HomeScreen.User_Name,"",editText.getText().toString(),2);
                }
            });
            lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animator) {

                }

                @Override
                public void onAnimationEnd(@NonNull Animator animator) {
                    editText.setText("");
                }

                @Override
                public void onAnimationCancel(@NonNull Animator animator) {

                }

                @Override
                public void onAnimationRepeat(@NonNull Animator animator) {

                }
            });
        } else if (mode == 3) {
           textView.setText("Help Desk(Report Bug)");
           editText.setHint("Enter the problem you facing in the application");
           lottieAnimationView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   lottieAnimationView.playAnimation();
                   SendEmail sendEmail=new SendEmail(HomeScreen.User_Email,HomeScreen.User_Name,"",editText.getText().toString(),3);
               }
           });
           lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
               @Override
               public void onAnimationStart(@NonNull Animator animator) {

               }

               @Override
               public void onAnimationEnd(@NonNull Animator animator) {
                   editText.setText("");
               }

               @Override
               public void onAnimationCancel(@NonNull Animator animator) {

               }

               @Override
               public void onAnimationRepeat(@NonNull Animator animator) {

               }
           });
        }


        return root;
    }
}