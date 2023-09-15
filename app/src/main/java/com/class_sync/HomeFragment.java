package com.class_sync;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment {

    ImageView atten;
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        atten = root.findViewById(R.id.TrackAttendance_CardView);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        atten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });
        return root;
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
                                    Toast.makeText(getActivity(),""+addresses.get(0).getAddressLine(0), Toast.LENGTH_LONG).show();
                                    if(addresses.get(0).getAddressLine(0).equalsIgnoreCase("FWCP+RF8, Satar Nagar, Hadapsar, Pune, Autadwadi " +
                                            "Handewadi, Maharashtra 411028, India"))
                                    {
                                        Toast.makeText(getActivity(), "You are in college", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getActivity(),  "You are not in the College, so your attendance will not be marked", Toast.LENGTH_SHORT).show();
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
}