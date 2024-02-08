package com.class_sync;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Home_Fragments.AddEbook;
import Home_Fragments.EbookFragments;
import Home_Fragments.MsbteResources_Fragement;
import Online_Courses.OnlineCourse_Home_Fragment;


public class HomeFragment extends Fragment  {
    public static final String ERROR_DETECTED = "No NFC Detected";
    private final static int REQUEST_CODE = 100;

    NfcAdapter nfcAdapter;
    Spinner subject,TimePeriod;
    IntentFilter writingTagFilter;
    PendingIntent pendingIntent;
    Tag mytag;
    View customDialogView;
    Button NFC;
    Context context;

    TextView AddEbook,user_Name;
    CardView MarkAttendance;
    int i=0;
    ViewGroup root;
    View decorView;
    String Subject_name,Time_period;
    LocationManager locationManager;
    ImageView TrackAttendance, MsbteResources, ebooks,online_course;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        customDialogView = inflater.inflate(R.layout.add_attendance_dialogbox_layout, null);
        findId();
        user_Name.setText(HomeScreen.User_Name);

        //Runtime permission
        if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }


        /// ---------------------------------------------Adapter for Subject Spinner------------------------------------------------------------------------
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.TY_Subject,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Subject_name=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "Please Select the category ", Toast.LENGTH_LONG).show();
            }
        });
        subject.setAdapter(adapter);

        /// ---------------------------------------------Adapter for TimePeriod Spinner------------------------------------------------------------------------

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.Time_period,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        TimePeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Time_period=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "Please Select the category ", Toast.LENGTH_LONG).show();
            }
        });
        TimePeriod.setAdapter(adapter2);

        TrackAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame,new TrackAttendanceFragment()).commit();

            }
        });



        context = getActivity();
        HomeScreen.bottomNavigation.show(1,true);
        decorView = getActivity().getWindow().getDecorView();
        HomeScreen.RootRelativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        AddEbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i==10)
                {
                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame,new AddEbook()).commit();
                }
            }
        });
        ebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new EbookFragments()).commit();

            }
        });
        online_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new OnlineCourse_Home_Fragment()).commit();
            }
        });
        MarkAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();

            }
        });

        MsbteResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new MsbteResources_Fragement()).commit();
            }
        });


//        NFC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if (mytag == null) {
//                        Toast.makeText(context, ERROR_DETECTED, Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "NFC DETECTED", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
        if (nfcAdapter == null) {
            Toast.makeText(context, "This device does not support NFC", Toast.LENGTH_SHORT).show();
        }
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);

        return root;
    }



    //----------------------Finding Id's of all the components on the Fragments------------------------------------
    void findId() {
        TrackAttendance = root.findViewById(R.id.TrackAttendance_CardView);
        AddEbook=root.findViewById(R.id.t1);
        online_course = root.findViewById(R.id.OnlineCourses);
        user_Name = root.findViewById(R.id.user_name);
        NFC = root.findViewById(R.id.ScanNFc);
        MarkAttendance = root.findViewById(R.id.MarkAttendance);
        MsbteResources = root.findViewById(R.id.MsbtePapers);
        ebooks = root.findViewById(R.id.Ebooks_imageView);


        subject = customDialogView.findViewById(R.id.AddAttendance_Subject);
        TimePeriod = customDialogView.findViewById(R.id.AddAttendance_TimePeriod);
    }




//-----------------------------Follwing Methods are used for Location Purpose------------------------
    private void getLastLocation() {
        //To get the last location of the user
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geo = new Geocoder(getContext(), Locale.getDefault());
                                List<Address> addresses = null;

                                try {
                                    addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                                    address.setText("Pune: "+addresses.get(0).getAddressLine(0));
                                    Toast.makeText(getActivity(), "" + addresses.get(0).getAddressLine(0), Toast.LENGTH_LONG).show();
                                    String College_Location_words[]={"Satar Nagar","Hadapsar" ,"Pune","Autadwadi","Handewadi"};
                                    if (containsWords(addresses.get(0).getAddressLine(0),College_Location_words)){
                                        showCustomDialog();
                                    } else {
                                        Toast.makeText(getActivity(), "You are not in the College, so your attendance will not be marked", Toast.LENGTH_SHORT).show();
                                    }


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

        } else {
            askPermission();

        }


    }

    private void askPermission() {
        //To take the permission from the user to read/get the current location
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        //ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {

                Toast.makeText(getContext(), "Please provide the required permission", Toast.LENGTH_SHORT).show();

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Location code ends -----------------------------------------------------------------------------------------------------------------------


    }


//    ----------------------------------------Code to hide navigation buttons-------------------------------------------
    public void onWindowFocusChanged(boolean hasFocus) {
        super.getActivity().onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }
    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;


    }



//--------------------Following Methods are used to verify Users Location and the College Location-------------------
public static boolean containsWord(String input, String word) {
    // Case-insensitive check for the presence of a word in the string
    return input.toLowerCase().contains(word.toLowerCase());
}

    public static boolean containsWords(String input, String[] words) {
        // Case-insensitive check for the presence of any of the specified words in the string
        for (String word : words) {
            if (containsWord(input, word)) {
                return true;
            }
        }
        return false;
    }


//------------------------- Following Method is used to retrive the current date -----------------------------------
    private String getCurrentDate() {
        // Get the current date and time
        Date currentDate = new Date();

        // Format the date using a desired pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

// ----------------------------------------------------Dialog Box to Add Attendance ------------------------------------------------------
    private void showCustomDialog() {


        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(customDialogView)
                .setTitle("Add Attendance")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference();


                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                               String Class = snapshot.child("users").child(HomeScreen.User_Name).child("class").getValue(String.class);
                               String Student_roll = snapshot.child("users").child(HomeScreen.User_Name).child("rollNo").getValue(String.class);

                               databaseReference.child("Attendance").child(getCurrentDate()).child(Class).child(Time_period).child(Subject_name).child(HomeScreen.User_Name)
                                                                             .child("name").setValue(HomeScreen.User_Name);
                                databaseReference.child("Attendance").child(getCurrentDate()).child(Class).child(Time_period).child(Subject_name).child(HomeScreen.User_Name)
                                        .child("rollNo").setValue(Student_roll).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(context, "Attendance has been Marked ", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle Cancel button click
                    }
                });

        // Show the AlertDialog
        AlertDialog customDialog = builder.create();
        customDialog.show();
    }
}