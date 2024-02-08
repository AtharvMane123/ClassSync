package com.class_sync;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class TrackAttendanceFragment extends Fragment {
ProgressBar progressBar;
EditText editText;
Button track;
int a = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_trackattendance, container, false);
        progressBar = root.findViewById(R.id.progressBar);
        editText = root.findViewById(R.id.PresentDays);
        track = root.findViewById(R.id.track);

       track.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(TextUtils.isEmpty(editText.getText().toString())){
                   editText.setError("Present days can't be empty");
               }
               else{
                   progressBar.setProgress(Integer.parseInt(editText.getText().toString()));

               }

           }
       });



        return root;
    }
}