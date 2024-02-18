package com.class_sync.TeacherActivities;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.class_sync.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentPreview_Activity extends AppCompatActivity {
    String Name, Gender, ClassName;
    TextView name, Class,email,Roll,FathersName,MothersName,FathersOccupation,FathersMob,MothersMob,Address,gender;
    CircleImageView profileImage;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_preview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Name = getIntent().getStringExtra("name");
        Gender = getIntent().getStringExtra("gender");
        ClassName = getIntent().getStringExtra("class");


        Roll = findViewById(R.id.StudentPreview_Roll);
        name = findViewById(R.id.StudentPreview_name);
        email = findViewById(R.id.StudentPreview_Email);
        profileImage = findViewById(R.id.StudentPreview_ProfileImage);
        Class = findViewById(R.id.StudentPreview_Class);
        FathersName = findViewById(R.id.StudentPreview_FathersName);
        FathersMob = findViewById(R.id.StudentPreview_FathersMob);
        FathersOccupation = findViewById(R.id.StudentPreview_FathersOccupation);
        MothersName = findViewById(R.id.StudentPreview_MothersName);
        MothersMob = findViewById(R.id.StudentPreview_MothersMob);
        gender = findViewById(R.id.StudentPreview_Gender);
        Address = findViewById(R.id.StudentPreview_Address);

        Class.setText(ClassName);
        name.setText(Name);
        if (Gender.equalsIgnoreCase("Male")) {
            profileImage.setImageResource(R.drawable.avatar);
        } else {
            profileImage.setImageResource(R.drawable.female_student);
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("StudentData").child("class").child(ClassName);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Roll.append(snapshot.child(Name).child("rollNo").getValue(String.class));
                email.append(snapshot.child(Name).child("email").getValue(String.class));
                FathersName.append(snapshot.child(Name).child("FatherName").getValue(String.class));
                FathersOccupation.append(snapshot.child(Name).child("FatherOccupation").getValue(String.class));
                FathersMob.append(snapshot.child(Name).child("FathersMobileNumber").getValue(String.class));
                MothersName.append(snapshot.child(Name).child("MothersName").getValue(String.class));
                MothersMob.append(snapshot.child(Name).child("MothersMobileNumber").getValue(String.class));
                Address.append(snapshot.child(Name).child("Address").getValue(String.class));
                gender.append(Gender);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}