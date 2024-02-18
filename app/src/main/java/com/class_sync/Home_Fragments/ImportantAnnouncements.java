package com.class_sync.Home_Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.class_sync.R;
import com.class_sync.RecyclerViews.Notification_ModelClass;
import com.class_sync.RecyclerViews.Notification_RecyclerViewAdapter;
import com.class_sync.RecyclerViews.ViewStudentDatabase_RecyclerAdapter;
import com.class_sync.RecyclerViews.ViewStudentDatabase_RecyclerViewModelClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ImportantAnnouncements extends Fragment {
    ArrayList<Notification_ModelClass> arrayList;
    Notification_RecyclerViewAdapter myAdapter;
    RecyclerView recyclerView;
    LottieAnimationView progressBar;
    ViewGroup root;
    public DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = (ViewGroup) inflater.inflate(R.layout.activity_notification, container, false);
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        arrayList = new ArrayList<Notification_ModelClass>();
        progressBar = root.findViewById(R.id.Notification_progressbar);
        recyclerView = root.findViewById(R.id.Notification_recycler);
        myAdapter = new Notification_RecyclerViewAdapter(arrayList);
        recyclerView.setAdapter(myAdapter);
        progressBar.setVisibility(View.VISIBLE);
        reference = FirebaseDatabase.getInstance().getReference("ImportantNotice");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Notification_ModelClass modelClass = snapshot1.getValue(Notification_ModelClass.class);
                    arrayList.add(modelClass);
                    progressBar.setVisibility(View.GONE);
                    reference.child("working").child("1");

                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
}