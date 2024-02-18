package com.class_sync;

import static com.class_sync.NotificationHelper.makeNotification;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class TeacherHomeScreen extends AppCompatActivity {
    CardView UploadAssignments, UploadWorkbooks, SendImportantNotice, ViewStudentDatabase,TeacherHomescreen_Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_screen);
        UploadAssignments = findViewById(R.id.TeacherFragement_UploadAssignments);
        UploadWorkbooks = findViewById(R.id.TeacherFragement_UploadWorkbooks);
        SendImportantNotice = findViewById(R.id.TeacherFragement_TrackStuentAttendance);
        ViewStudentDatabase = findViewById(R.id.TeacherFragement_ViewStudentDatabase);
        TeacherHomescreen_Logout = findViewById(R.id.TeacherHomeScreen_Logout);
//        displayNotification();
//        NotificationHelper.showNotification(getApplicationContext(),"Hello","Welcome Back Atharv");



        if (Build.VERSION.SDK_INT >= Build. VERSION_CODES. TIRAMISU) {
            if (ContextCompat.checkSelfPermission (getApplicationContext(),
                    android. Manifest.permission. POST_NOTIFICATIONS) !=
            PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions (TeacherHomeScreen.this,
                        new String[] {Manifest.permission.POST_NOTIFICATIONS},  101);
            }
        }


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

        SendImportantNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.class_sync.TeacherActivities.SendImportantNotices.class));

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