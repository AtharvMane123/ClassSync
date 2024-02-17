package com.class_sync.TeacherActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.class_sync.R;
import com.class_sync.RecyclerViews.ViewStudentDatabase_RecyclerAdapter;
import com.class_sync.RecyclerViews.ViewStudentDatabase_RecyclerViewModelClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewStudentDatabase extends AppCompatActivity {
RecyclerView recyclerView;
    ViewStudentDatabase_RecyclerAdapter myAdapter;
    LottieAnimationView progressBar;
    public static DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
ArrayList<ViewStudentDatabase_RecyclerViewModelClass> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_database);
        arrayList = new ArrayList<ViewStudentDatabase_RecyclerViewModelClass>();
        progressBar=findViewById(R.id.ViewStudentDatabase_progress);
        recyclerView = findViewById(R.id.ViewStudent_recycler);



        arrayList.clear();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren())
                {
                    ViewStudentDatabase_RecyclerViewModelClass modelClass=snapshot1.getValue(ViewStudentDatabase_RecyclerViewModelClass.class);
                    arrayList.add(modelClass);
                    progressBar.setVisibility(View.GONE);
                    reference.child("working").child("1");
                }myAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myAdapter = new ViewStudentDatabase_RecyclerAdapter(arrayList, new ViewStudentDatabase_RecyclerAdapter.RecyclerViewItemClickListener1() {
            @Override
            public void ItemClick(String Name, String Class) {

            }
        });
        recyclerView.setAdapter(myAdapter);

    }
}