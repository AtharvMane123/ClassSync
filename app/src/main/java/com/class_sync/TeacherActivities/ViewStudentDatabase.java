package com.class_sync.TeacherActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Objects;

public class ViewStudentDatabase extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewStudentDatabase_RecyclerAdapter myAdapter;
    LottieAnimationView progressBar;
    Spinner Spinner_Class;
    String Student_Class;
   public String ClassName;
    public static DatabaseReference reference;
    ArrayList<ViewStudentDatabase_RecyclerViewModelClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_database);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        arrayList = new ArrayList<ViewStudentDatabase_RecyclerViewModelClass>();
        progressBar = findViewById(R.id.ViewStudentDatabase_progress);
        recyclerView = findViewById(R.id.ViewStudent_recycler);
        Spinner_Class = findViewById(R.id.ViewStudentDatabase_Spinner_Class);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Class_Spinner_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Student_Class = adapterView.getItemAtPosition(i).toString();
                if (Objects.equals(adapterView.getItemAtPosition(i).toString(), "FYCO1")) {
                    DisplayRecyclerView("FYCO1");
                    ClassName = "FYCO1";
                } else if (Objects.equals(adapterView.getItemAtPosition(i).toString(), "FYCO2")) {
                    DisplayRecyclerView("FYCO2");
                    ClassName = "FYCO2";
                } else if (Objects.equals(adapterView.getItemAtPosition(i).toString(), "FYCO3")) {
                    DisplayRecyclerView("FYCO3");
                    ClassName = "FYCO3";
                } else if (Objects.equals(adapterView.getItemAtPosition(i).toString(), "SYCO1")) {
                    DisplayRecyclerView("SYCO1");
                    ClassName = "SYCO1";
                } else if (Objects.equals(adapterView.getItemAtPosition(i).toString(), "SYCO2")) {
                    DisplayRecyclerView("SYCO2");
                    ClassName = "SYCO2";
                } else if (Objects.equals(adapterView.getItemAtPosition(i).toString(), "SYCO3")) {
                    DisplayRecyclerView("SYCO3");
                    ClassName = "SYCO3";
                } else if (Objects.equals(adapterView.getItemAtPosition(i).toString(), "TYCO1")) {
                    DisplayRecyclerView("TYCO1");
                    ClassName = "TYCO1";
                } else if (Objects.equals(adapterView.getItemAtPosition(i).toString(), "TYCO2")) {
                    DisplayRecyclerView("TYCO2   ");
                    ClassName = "TYCO2";
                } else if (Objects.equals(adapterView.getItemAtPosition(i).toString(), "TYCO3")) {
                    DisplayRecyclerView("TYCO3");
                    ClassName = "TYCO3";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Please Select the category ", Toast.LENGTH_LONG).show();
            }
        });
        Spinner_Class.setAdapter(adapter);

        arrayList.clear();

        myAdapter = new ViewStudentDatabase_RecyclerAdapter(arrayList, new ViewStudentDatabase_RecyclerAdapter.RecyclerViewItemClickListener1() {
            @Override
            public void ItemClick(String Name, String Class, int position) {
                Intent intent = new Intent(getApplicationContext(), StudentPreview_Activity.class);
                intent.putExtra("name", Name);
                intent.putExtra("class",ClassName);
                intent.putExtra("gender", arrayList.get(position).getGender());
                 startActivity(intent);

            }
        });
        recyclerView.setAdapter(myAdapter);

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
}