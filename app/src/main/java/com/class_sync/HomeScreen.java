package com.class_sync;

import static android.content.ContentValues.TAG;
import static com.class_sync.NotificationHelper.makeNotification;

import android.Manifest;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.class_sync.Home_Fragments.EbookFragments;
import com.class_sync.Home_Fragments.ImportantAnnouncements;
import com.class_sync.Online_Courses.OnlineCourse_Home_Fragment;
import com.class_sync.Utility.NetworkChangedListener;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.location.LocationRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeScreen extends AppCompatActivity {
    public static MeowBottomNavigation bottomNavigation;
    public static String Name;
    NetworkChangedListener networkChangedListener = new NetworkChangedListener();

    public static String User_Email;
    public static String User_Name;
    DatabaseReference databaseReference;
    int i;


    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor sharedPreferencesEditor;

    private LocationRequest locationRequest;
    private ChildEventListener childEventListener;
    public static final int LOCATION_CHECK_SETTINGS = 1001;
    public static RelativeLayout RootRelativeLayout;
    public static String Email;
    public static int BiodataForm = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        RootRelativeLayout = findViewById(R.id.RootRelativeLayout);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        databaseReference = FirebaseDatabase.getInstance().getReference("ImportantNotice");

        BiodataForm = 0;





        if (getIntent().getStringExtra("OpenImportantNotice")!=null) {
            if (getIntent().getStringExtra("OpenImportantNotice").equals("OpenImportantNotice")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ImportantAnnouncements()).addToBackStack("").commit();
            }
        }
        if (getIntent().getStringExtra("Register")!=null) {



                if (getIntent().getStringExtra("Register").equals("Register")) {
                    BiodataForm = 1;
                }
            }

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // This method will be called whenever new data is added to the database
                String key = dataSnapshot.getKey();
                Object value = dataSnapshot.getValue();
                // You can process the added data here
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                makeNotification(getApplicationContext(), "Class_Sync", "Important Announcement ");

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Handle data removal
                makeNotification(getApplicationContext(), "Class_Sync", "Important Announcement ");

            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                makeNotification(getApplicationContext(), "Class_Sync", "Important Announcement ");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        };

        // Add ChildEventListener to listen for changes in the database
        databaseReference.addChildEventListener(childEventListener);


        sharedPreferences = getSharedPreferences("userLoggedIn", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        User_Email = sharedPreferences.getString("Email", "");
        User_Name = sharedPreferences.getString("Name", "");

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_chat_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_hub_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_person_24));
        bottomNavigation.show(1, true);
//          getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch (model.getId()) {
                    case 1:
                        getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new HomeFragment()).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new EbookFragments()).commit();

                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new OnlineCourse_Home_Fragment()).commit();

                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new UserFragment()).commit();

                        break;
                }
                return null;
            }
        });




    }



    @Override
    public void onBackPressed() {
        Log.e(TAG, "onKeyDown: ");
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the ChildEventListener when the activity is destroyed to avoid memory leaks
        if (databaseReference != null && childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
        }
    }
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangedListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangedListener);
        super.onStop();
    }
}