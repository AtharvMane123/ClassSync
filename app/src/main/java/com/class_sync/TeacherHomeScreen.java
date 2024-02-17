package com.class_sync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.class_sync.TeacherActivities.ViewStudentDatabase;

public class TeacherHomeScreen extends AppCompatActivity {
    CardView UploadAssignments, UploadWorkbooks, TrackStudentAttendance, ViewStudentDatabase,TeacherHomescreen_Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_screen);
        UploadAssignments = findViewById(R.id.TeacherFragement_UploadAssignments);
        UploadWorkbooks = findViewById(R.id.TeacherFragement_UploadWorkbooks);
        TrackStudentAttendance = findViewById(R.id.TeacherFragement_TrackStuentAttendance);
        ViewStudentDatabase = findViewById(R.id.TeacherFragement_ViewStudentDatabase);
        TeacherHomescreen_Logout = findViewById(R.id.TeacherHomeScreen_Logout);

        TeacherHomescreen_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAsTeacher.sharedPreferencesEditor.clear().commit();
                startActivity(new Intent(getApplicationContext(),LoginAsTeacher.class));
                finish();
            }
        });

        UploadWorkbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.class_sync.TeacherActivities.UploadWorkbooks.class));
            }
        });

        UploadAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), com.class_sync.TeacherActivities.UploadAssignments.class));
            }
        });

        TrackStudentAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TeacherHomeScreen.this, "This Feature is not available for now", Toast.LENGTH_SHORT).show();
            }
        });

        ViewStudentDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),com.class_sync.TeacherActivities.ViewStudentDatabase.class));
            }
        });

    }
}