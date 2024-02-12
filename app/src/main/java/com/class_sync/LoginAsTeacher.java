package com.class_sync;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class LoginAsTeacher extends AppCompatActivity {
EditText PhoneNumber_EditText,Password_EditText,OTP_EditText;
TextView SendOTP,LoginAsStudent;
CardView login;
FirebaseAuth auth = FirebaseAuth.getInstance();

Long timeoutSeconds = 60L;
String phoneNumber;
String verificationCode;
PhoneAuthProvider.ForceResendingToken resendingToken;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor sharedPreferencesEditor;

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

        PhoneNumber_EditText.setText("+91  ");


        LoginActivity.sharedPreferences = getSharedPreferences("TeacherLoggedIn", MODE_PRIVATE);
        LoginActivity.sharedPreferencesEditor = LoginActivity.sharedPreferences.edit();

        sharedPreferences = getSharedPreferences("TeacherLoggedIn", MODE_PRIVATE);
        sharedPreferencesEditor = LoginActivity.sharedPreferences.edit();

        if (sharedPreferences.getBoolean("Teacherlogin", false)) {
            startActivity(new Intent(getApplicationContext(), TeacherHomeScreen.class));
            finish();
        }
        if (LoginActivity.sharedPreferences.getBoolean("Teacherlogin", false)) {
            startActivity(new Intent(getApplicationContext(), TeacherHomeScreen.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredOTP = OTP_EditText.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,enteredOTP);
                signIn(credential);
//                startActivity(new Intent(getApplicationContext(),TeacherHomeScreen.class));
            }
        });
        SendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OTP_EditText.setVisibility(View.VISIBLE);
                sendOtp(PhoneNumber_EditText.getText().toString()  ,false);
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
   void sendOtp(String phoneNumber,Boolean isResend){
       PhoneAuthOptions.Builder builder =
               PhoneAuthOptions.newBuilder(auth)
                       .setPhoneNumber("+91"+PhoneNumber_EditText.getText().toString())
                       .setTimeout(timeoutSeconds, TimeUnit.SECONDS)
                       .setActivity(this)
                       .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                           @Override
                           public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                               signIn(phoneAuthCredential);
                           }

                           @Override
                           public void onVerificationFailed(@NonNull FirebaseException e) {
                               Toast.makeText(LoginAsTeacher.this, "OTP verification failed", Toast.LENGTH_SHORT).show();
                           }

                           @Override
                           public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               super.onCodeSent(s, forceResendingToken);
                               verificationCode = s;
                               resendingToken = forceResendingToken;
                               Toast.makeText(LoginAsTeacher.this, "OTP sent Successfully", Toast.LENGTH_SHORT).show();
                           }
                       });
       if(isResend)
       {
           PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
       }
       else {
           PhoneAuthProvider.verifyPhoneNumber(builder.build());
       }
    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(getApplicationContext(),TeacherHomeScreen.class));
                    LoginActivity.sharedPreferencesEditor.putBoolean("Teacherlogin", true);
                    LoginActivity.sharedPreferencesEditor.putBoolean("Teacherlogin", true);
                    LoginActivity.sharedPreferencesEditor.putString("PhoneNumber", PhoneNumber_EditText.getText().toString());
                    LoginActivity.sharedPreferencesEditor.commit();

                    sharedPreferencesEditor.putBoolean("Teacherlogin", true);
                    sharedPreferencesEditor.putBoolean("Teacherlogin", true);
                    sharedPreferencesEditor.putString("PhoneNumber", PhoneNumber_EditText.getText().toString());
                    sharedPreferencesEditor.commit();
                 }
                else {
                    Toast.makeText(LoginAsTeacher.this, "OTP verification Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}