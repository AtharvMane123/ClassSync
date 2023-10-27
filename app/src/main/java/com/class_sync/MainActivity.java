package com.class_sync;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    LottieAnimationView l1;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        l1 = findViewById(R.id.logoAnim);
        t1 = findViewById(R.id.logoName);
        Animation m1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logoanim);
        Animation m2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logoanim2);
        l1.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
//
                Intent intent = new Intent(getApplicationContext(), newlyInstalled.class);
                startActivity(intent);
                finish();
            }



            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            i++;
            if (i == 15) {
                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                finish();
            }
        }
        return true;
    }
}