package com.class_sync.TeacherActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.class_sync.R;
import com.class_sync.RecyclerViews.TeacherClass_Adapter;
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
    TeacherClass_Adapter ClassAdapter;
    LottieAnimationView progressBar;
    ImageView backButton;

    public String ClassName;
    public static DatabaseReference reference;
    ArrayList<ViewStudentDatabase_RecyclerViewModelClass> arrayList;
    ArrayList<String> arrayListClass = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_database);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        arrayList = new ArrayList<ViewStudentDatabase_RecyclerViewModelClass>();
        progressBar = findViewById(R.id.ViewStudentDatabase_progress);
        recyclerView = findViewById(R.id.ViewStudent_recycler);
        backButton = findViewById(R.id.backButton);


        ClassAdapter = new TeacherClass_Adapter(arrayListClass, new TeacherClass_Adapter.RecyclerViewItemClickListener() {
            @Override
            public void ItemClick(String s, int position) {
                ClassName = s;
                recyclerView.setAdapter(myAdapter);
                DisplayRecyclerView(s);
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        reference = FirebaseDatabase.getInstance().getReference("Teachers Data").child("Atharv Mane").child("class");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayListClass.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Log.e("HELLO", "key: " + snapshot1.getKey());

                    arrayListClass.add(snapshot1.getKey());
                    progressBar.setVisibility(View.GONE);
//                    reference.child("working").child("1");
                }
                ClassAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        recyclerView.setAdapter(ClassAdapter);
        arrayList.clear();
        myAdapter = new ViewStudentDatabase_RecyclerAdapter(arrayList, new ViewStudentDatabase_RecyclerAdapter.RecyclerViewItemClickListener1() {
            @Override
            public void ItemClick(String Name, String Class, int position) {
                Intent intent = new Intent(getApplicationContext(), StudentPreview_Activity.class);
                intent.putExtra("name", Name);
                intent.putExtra("class", ClassName);
                intent.putExtra("gender", arrayList.get(position).getGender());
                startActivity(intent);

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              back();
            }
        });

    }

    private void DisplayRecyclerView(String Class) {
        arrayList.clear();
        progressBar.setVisibility(View.VISIBLE);
        reference = FirebaseDatabase.getInstance().getReference("StudentData").child("class").child(Class);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    ViewStudentDatabase_RecyclerViewModelClass modelClass = snapshot1.getValue(ViewStudentDatabase_RecyclerViewModelClass.class);
                    modelClass.setGender(snapshot1.child("gender").getValue(String.class));
                    arrayList.add(modelClass);
                    progressBar.setVisibility(View.GONE);
                    reference.child("working").child("1");

                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
public void back()
{
    super.onBackPressed();
}
    @Override
    public void onBackPressed() {
        recyclerView.setAdapter(ClassAdapter);
        progressBar.setVisibility(View.GONE);
    }
}