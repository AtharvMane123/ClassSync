package com.class_sync;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;

import Home_Fragments.EbookFragments;
import Online_Courses.OnlineCourse_Home_Fragment;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeScreen extends AppCompatActivity {
    public static MeowBottomNavigation bottomNavigation;
    public static String Name;
    public static String User_Email;
    public static String User_Name;
    int i;



    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor sharedPreferencesEditor;

    private LocationRequest locationRequest;
    public  static  final int LOCATION_CHECK_SETTINGS = 1001;
    public static RelativeLayout RootRelativeLayout;
    public static String Email;

MaterialCardView assignments,attendance,groupChatting,askChatGpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        RootRelativeLayout=findViewById(R.id.RootRelativeLayout);
        bottomNavigation=findViewById(R.id.bottomNavigation);


        sharedPreferences = getSharedPreferences("userLoggedIn", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        User_Email = sharedPreferences.getString("Email", "ok");
        User_Name = sharedPreferences.getString("Name", "okk");

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_chat_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_hub_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_person_24));
        bottomNavigation.show(1,true);
//          getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame,new EbookFragments()).commit();

                break;
            case 3:
                getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame,new OnlineCourse_Home_Fragment()).commit();

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

//        locationRequest = com.google.android.gms.location.LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(5000);
//        locationRequest.setFastestInterval(2000);
//
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest);
//        builder.setAlwaysShow(true);
//
//        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
//                .checkLocationSettings(builder.build());
//
//
//        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
//            @Override
//            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
//                try {
//                    LocationSettingsResponse response = task.getResult(ApiException.class);
//
//                } catch (ApiException e) {
//                    switch (e.getStatusCode()){
//                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//
//                            try {
//                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
//                                resolvableApiException.startResolutionForResult(HomeScreen.this,LOCATION_CHECK_SETTINGS);
//                            } catch (IntentSender.SendIntentException ex) {
//
//                            }
//                            break;
//                        case  LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                            break;
//                    }
//
//                }
//            }
//        });
//
//
//
//


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(resultCode == LOCATION_CHECK_SETTINGS){
//            switch (resultCode){
//                case  Activity.RESULT_OK:
//                    break;
//                case  Activity.RESULT_CANCELED:
//                    Toast.makeText(this, "Please Turn on your Location", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


    @Override
    public void onBackPressed() {
        Log.e(TAG, "onKeyDown: ");
        super.onBackPressed();
    }
}