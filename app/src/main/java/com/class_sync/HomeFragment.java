package com.class_sync;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import Home_Fragments.AddEbook;
import Home_Fragments.EbookFragments;
import Home_Fragments.MsbteResources_Fragement;


public class HomeFragment extends Fragment {
    public static final String ERROR_DETECTED = "No NFC Detected";
    private final static int REQUEST_CODE = 100;
    NfcAdapter nfcAdapter;
    IntentFilter writingTagFilter;
    PendingIntent pendingIntent;
    Tag mytag;

    Button NFC;
    Context context;
    TextView AddEbook;
    int i=0;
    ViewGroup root;
    View decorView;

    ImageView atten, MsbteResources, ebooks;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        findId();
        context = getActivity();

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();
        databaseReference.child("atharv").child("mane").setValue(new String("TYCO3"));
        decorView = getActivity().getWindow().getDecorView();
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
        atten.setOnClickListener(new View.OnClickListener() {
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

    //Finding Id's of all the components on the Fragments
    void findId() {
        atten = root.findViewById(R.id.TrackAttendance_CardView);
        AddEbook=root.findViewById(R.id.t1);
        NFC = root.findViewById(R.id.ScanNFc);
        MsbteResources = root.findViewById(R.id.MsbtePapers);
        ebooks = root.findViewById(R.id.Ebooks_imageView);
    }


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
                                    if (addresses.get(0).getAddressLine(0).equalsIgnoreCase("FWCP+RF8, Satar Nagar, Hadapsar, Pune, Autadwadi " +
                                            "Handewadi, Maharashtra 411028, India")) {
                                        Toast.makeText(getActivity(), "You are in college", Toast.LENGTH_SHORT).show();
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
//    -------------------Code to hide navigation buttons------------------------
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

}