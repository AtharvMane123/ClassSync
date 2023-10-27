package com.class_sync;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.card.MaterialCardView;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeScreen extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    public static String Name;
    public static String Email;

MaterialCardView assignments,attendance,groupChatting,askChatGpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_chat_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_hub_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_person_24));
        bottomNavigation.show(1,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new HomeFragment()).commit();
    bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
    @Override
    public Unit invoke(MeowBottomNavigation.Model model) {

        switch (model.getId())
        {
            case 1:
                getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame,new HomeFragment()).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame,new MessengerFragment()).commit();

                break;
            case 3:
                Toast.makeText(HomeScreen.this, "UpComing Fragment", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame,new UserFragment()).commit();

                break;
        }
        return null;
    }
});

//        attendance=findViewById(R.id.TrackAttendance_CardView);
//        groupChatting=findViewById(R.id.GroupChatting_CardView);
//        assignments=findViewById(R.id.Assignments_CardView);
//        askChatGpt=findViewById(R.id.AskChatGpt_CardView);
//
//        attendance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(HomeScreen.this, "Track Attendance", Toast.LENGTH_SHORT).show();
//            }
//        });
//        groupChatting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(HomeScreen.this, "Group Chatting", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        assignments.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(HomeScreen.this, "Assignments", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        askChatGpt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(HomeScreen.this, "Ask ChatGpt", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//

    }
}