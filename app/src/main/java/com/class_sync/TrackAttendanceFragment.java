package com.class_sync;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.security.identity.CredentialDataResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TrackAttendanceFragment extends Fragment {
    LinearProgressIndicator progressBar, LastMonth_progressBar;

    TextView Attendance_in_percentage,Attendance_in_word;
    Button track;
    LineChart lineChart;
    List<String> xvalues;
    private DatabaseReference databaseReference;
    int a = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_trackattendance, container, false);
        progressBar = root.findViewById(R.id.progressBar);
        track = root.findViewById(R.id.track);
        lineChart = root.findViewById(R.id.LineChart);
        Attendance_in_percentage = root.findViewById(R.id.Attendance_percentage);
        Attendance_in_word = root.findViewById(R.id.Attendance_in_word);











        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(HomeScreen.User_Name).child("Total Attendance").child(getCurrentMonth_with_Year());

        // Retrieve current value for the specific day
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int Attendance = snapshot.child("Total Attendance").getValue(Integer.class);
                int TotalAttendance = ((Attendance * 100) / 30);

                int attendance_half = ((Attendance/2)-5);
                lineChart(attendance_half,Attendance);

                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", TotalAttendance);
                progressAnimator.setDuration(1000); // 10 seconds
                progressAnimator.start();
                Attendance_in_word.setText("Your have attended "+Attendance +" out 0f 30 sessions" );
                progressAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {

                        Attendance_in_percentage.setText(TotalAttendance + "%");

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
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                        if (TextUtils.isEmpty(editText.getText().toString())) {
//                            editText.setError("Present days can't be empty");
//                        } else {


                incrementValue();

//                        }

            }
        });


        return root;
    }

    private String getCurrentMonth_with_Year() {
        Date currentDate = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        String currentMonth = monthFormat.format(currentDate);
        String currentYear = yearFormat.format(currentDate);
        return currentMonth + "-" + currentYear;
    }


    private void incrementValue() {
        databaseReference.child("Total Attendance") // Specify the key of the value you want to increment
                .setValue(ServerValue.increment(1)) // Increment the value by 1
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Value incremented successfully
                        Toast.makeText(getActivity(), "Value incremented successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Toast.makeText(getActivity(), "Failed to increment value: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void lineChart(float Attendance1,float Attendance2){
        lineChart.setVisibility(View.VISIBLE);
        Description description = new Description();
        description.setText("Students Record");
        description.setPosition(150f,15f);
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false);
        xvalues = Arrays.asList("Atharv","omkar","manish","yash","prithviraj");

        XAxis xaxis=lineChart.getXAxis();
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setValueFormatter(new IndexAxisValueFormatter(xvalues));
        xaxis.setLabelCount(4);
        xaxis.setGranularity(1f);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(30f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        List<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0,0f));
        entries.add(new Entry(1,Attendance1));
        entries.add(new Entry(2,Attendance2));
//        entries.add(new Entry(3,25f));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0,5f));
        entries2.add(new Entry(1,15f));
        entries2.add(new Entry(2,25f));
        entries2.add(new Entry(3,35f));


        LineDataSet dataSet1 = new LineDataSet(entries,"Attendance");
        dataSet1.setColor(Color.BLUE);

//        LineDataSet dataSet2 = new LineDataSet(entries2,"Science");
//        dataSet2.setColor(Color.BLUE);

        LineData lineData = new LineData(dataSet1);
        lineChart.setData(lineData);
        lineChart.animateX(1000);
        lineChart.invalidate();

    }
}