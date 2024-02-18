package com.class_sync;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TrackAttendanceFragment extends Fragment {
    ProgressBar progressBar;
    EditText editText;
    Button track;
    private DatabaseReference databaseRef;
    int a = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_trackattendance, container, false);
        progressBar = root.findViewById(R.id.progressBar);
        editText = root.findViewById(R.id.PresentDays);
        track = root.findViewById(R.id.track);

        databaseRef = FirebaseDatabase.getInstance().getReference().child("daily_values").child(getCurrentDate());

        // Retrieve current value for the specific day

                track.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(editText.getText().toString())) {
                            editText.setError("Present days can't be empty");
                        } else {
                            progressBar.setProgress(Integer.parseInt(editText.getText().toString()));

                        }

                    }
                });


        return root;
    }
            private String getCurrentDate() {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();
                return dateFormat.format(date);
            }
}