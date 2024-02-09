package com.class_sync;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginAsTeacher extends AppCompatActivity {
EditText PhoneNumber_EditText,Password_EditText,OTP_EditText;
TextView SendOTP,LoginAsStudent;
CardView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_teacher);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LoginAsStudent = findViewById(R.id.LoginAsStudent);
        PhoneNumber_EditText = findViewById(R.id.TeacherPhone_EditText);
        Password_EditText = findViewById(R.id.TeacherPassword_EditText);
        OTP_EditText = findViewById(R.id.TeacherOTP_EditText);
        SendOTP = findViewById(R.id.sendOtp);
        login = findViewById(R.id.TeacherLogin_cardView);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TeacherHomeScreen.class));
            }
        });
        SendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OTP_EditText.setVisibility(View.VISIBLE);
            }
        });





    LoginAsStudent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
    });
    }
}